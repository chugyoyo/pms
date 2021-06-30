package com.xin.controller.api;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xin.config.AppConfig;
import com.xin.dao.ClientDao;
import com.xin.dto.api.CreateClientDTO;
import com.xin.dto.api.LoginDTO;
import com.xin.dto.api.UpdatePasswordDTO;
import com.xin.enums.ClientStatus;
import com.xin.po.Client;
import com.xin.service.ClientService;
import com.xin.web.WebContext;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 吴泽欣
 * @since 2021/1/5 16:18
 */
@Slf4j
@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Resource
    private ClientService clientService;
    @Resource
    private ClientDao clientDao;
    @Resource
    private AppConfig appConfig;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/login")
    public R login(@RequestBody @Validated LoginDTO loginDTO,
                   HttpServletRequest httpServletRequest) {
        log.info("loginDTO:{}", loginDTO);
        // 校对验证码
        String verificationCodeIn = (String) httpServletRequest.getSession().getAttribute("verificationCode");
        // isEmpty是各种“空”的情况都有
        if (StringUtils.isEmpty(verificationCodeIn) || !verificationCodeIn.equals(loginDTO.getVerificationCode())) {
            log.info("verificationCodeIn:{}",verificationCodeIn);
            return R.error("验证码错误，或已失效");
        }
        // 校对登录信息
        QueryWrapper<Client> query = new QueryWrapper<>();
        query.eq("client_email", loginDTO.getEmail());
        /**
         * 只在登录的时候用到筛选掉被禁的用户。
         * 其他时候只需要正常查找即可，因为用户是从”存在“用户中查找的
         */
        query.eq("client_status", ClientStatus.NORMAL.getCode());
        Client client = clientService.getOne(query);
        if (client == null) {
            return R.error(Consts.FAILED_CODE, "账号不存在或者被禁");
        }
        System.out.println(SecureUtil.md5(loginDTO.getPassword()));
        if (!client.getClientPassword().equals(SecureUtil.md5(loginDTO.getPassword()))) {
            return R.error(Consts.FAILED_CODE, "密码错误");
        }
        //生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", client.getClientId());
        claims.put("email", client.getClientEmail());
        String token = JWTUtil.createJWT(claims, appConfig.getJwtSubject(), appConfig.getJwtSecret(), appConfig.getJwtLife());
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return R.data(tokenMap);
    }

    @PostMapping("/add")
    public R registerClient(@RequestBody @Validated CreateClientDTO createClientDTO) {
        log.debug("createUserDTO:{}", createClientDTO);
        // 检查用户名
        if (clientDao.existsClientName(createClientDTO.getName())) {
            return R.error(Consts.FAILED_CODE, "用户名已被占用");
        }
        if (clientDao.existsClientEmail(createClientDTO.getEmail())) {
            return R.error(Consts.FAILED_CODE, "邮箱已被注册");
        }
        if(!createClientDTO.getEmailCode().equals
                (redisTemplate.opsForValue().get(createClientDTO.getEmail()))){
            return R.error("邮箱验证码错误！");
        }
        // 清除缓存
        redisTemplate.delete(createClientDTO.getEmail());
        // 插入数据
        Client newClient = new Client();
        newClient.setClientEmail(createClientDTO.getEmail());
        newClient.setClientName(createClientDTO.getName());
        newClient.setClientPassword(SecureUtil.md5(createClientDTO.getPassword()));
        log.debug("newClient:{}", newClient);
        // 保存用户
        boolean success = clientService.save(newClient);
        if (!success) {
            return R.error("未知原因注册失败");
        }
        // 生成jwt(登录)
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", clientDao.getClientIdByEmail(createClientDTO.getEmail()));
        claims.put("email", createClientDTO.getEmail());
        String token = JWTUtil.createJWT(claims, appConfig.getJwtSubject(), appConfig.getJwtSecret(), appConfig.getJwtLife());
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return R.data(tokenMap);
    }

    /**
     * 通过AdminWebContext线程存下的token的信息来取得用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public R getClient() {
        int clientId = WebContext.getClientId();
        Client client = clientService.getById(clientId);
        if (client == null) {
            return R.error("查找不到账号信息");
        }
        log.info("getClient:client=" + client);
        return R.data(client);
    }

    @PutMapping("/name")
    public R updateClientName(@RequestParam String clientName) {
        int clientId = WebContext.getClientId();
        // 邮箱被占用
        QueryWrapper<Client> query = new QueryWrapper<>();
        query.eq("client_name", clientName);
        query.ne("client_id", clientId);
        Client clientCheck = clientService.getOne(query);
        if (clientCheck != null) {
            return R.error(Consts.FAILED_CODE, "名字已被占用");
        }
        // 改名
        boolean success = clientDao.updateClientName(clientId, clientName);
        if (success) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/updatePassword")
    public R updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        String newPassword = updatePasswordDTO.getNewPassword();
        String oldPassword = updatePasswordDTO.getOldPassword();
        int clientId = WebContext.getClientId();
        Client client = clientService.getById(clientId);
        if (client == null) {
            return R.error("找不到账号信息");
        }
        if (!Objects.equals(SecureUtil.md5(oldPassword), client.getClientPassword())){
            return R.error("密码错误");
        }
        if(newPassword.equals(oldPassword)){
            return R.error("新密码不能与旧密码一致");
        }
        Boolean success = clientDao.updatePasswordByClientId(clientId, newPassword);
        if(success){
            return R.ok("更新密码成功");
        }else {
            return R.error("更新密码失败");
        }
    }
}
