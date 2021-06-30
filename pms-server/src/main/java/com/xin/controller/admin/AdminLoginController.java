package com.xin.controller.admin;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xin.config.AppConfig;
import com.xin.dao.AdminDao;
import com.xin.dto.admin.AdminLoginDTO;
import com.xin.po.Admin;
import com.xin.service.AdminService;
import com.xin.web.AdminWebContext;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴泽欣
 * @since 2021/1/8 13:12
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminLoginController {
    @Resource
    private AdminService adminService;

    @Resource
    private AppConfig appConfig;

    /**
     * 管理员登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody @Validated AdminLoginDTO loginDTO) {
        log.debug("loginDTO:{}", loginDTO);
        QueryWrapper<Admin> query = new QueryWrapper<>();
        query.eq("admin_name", loginDTO.getAdminName());
        Admin admin = adminService.getOne(query);
        if (admin == null) {
            return R.error(Consts.FAILED_CODE, "管理员名不存在");
        }
        System.out.println(SecureUtil.md5(loginDTO.getAdminPassword()));
        if (!admin.getAdminPassword().equals(SecureUtil.md5(loginDTO.getAdminPassword()))) {
            return R.error(Consts.FAILED_CODE, "管理员密码错误");
        }
        //生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("adminId", admin.getAdminId());
        claims.put("adminRole", admin.getAdminRole());
        String token = JWTUtil.createJWT(claims, appConfig.getJwtSubject(), appConfig.getJwtSecret(), appConfig.getJwtLife());
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return R.data(tokenMap);
    }

    /**
     * 根据token获取登录后的姓名
     *
     * @return
     */
    @GetMapping("/info")
    public R getInfo() {
        int adminId = AdminWebContext.getAdminId();
        Admin admin = adminService.getById(adminId);
        log.info("getInfo:admin=>{}", admin);
        return R.data(admin);
    }
}
