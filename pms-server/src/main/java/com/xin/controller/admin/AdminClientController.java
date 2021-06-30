package com.xin.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.dao.AdminDao;
import com.xin.dao.ClientDao;
import com.xin.enums.Role;
import com.xin.po.Client;
import com.xin.service.ClientService;
import com.xin.service.impl.ClientServiceImpl;
import com.xin.web.AdminWebContext;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 吴泽欣
 * @since 2021/1/7 17:12
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/client")
public class AdminClientController {

//    @Resource
//    private ClientDao clientDao;
//    @Resource
//    private ClientService clientService;
//    @Resource
//    private AdminDao adminDao;
//
//    /**
//     * 获取用户信息
//     * 所有管理员
//     * @param keywords
//     * @param page
//     * @return
//     */
//    @GetMapping("/list")
//    public R pageSearchForClients(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
//        // 管理员权限验证
//        if (!authorityForClient()) {
//            return R.error("您没有管理客户的权限");
//        }
//        Page<Client> pageData = clientDao.pageSearch(keywords, page);
//        return R.data(pageData);
//    }
//
//    /**
//     * 更新客户信息
//     * 权限：root、clientAdmin
//     * @param client
//     * @return
//     */
//    @PostMapping("/update")
//    public R updateClients(@RequestBody @Validated Client client) {
//        // 管理员权限验证
//        if (!authorityForClient()) {
//            return R.error("您没有管理客户的权限");
//        }
//        // 检验格式？？？？（注解）
//        // 更新操作
//        boolean success = clientService.updateById(client);
//        if(success){
//            return R.ok();
//        }else {
//            return R.error("更新客户信息成功！");
//        }
//    }
//
//    /**
//     * 更新客户信息
//     * 权限：root、clientAdmin
//     * @param clientId
//     * @return
//     */
//    @DeleteMapping("/{clientId}")
//    public R deleteClients(@PathVariable("clientId") Integer clientId){
//        // 管理员权限验证
//        if (!authorityForClient()) {
//            return R.error("您没有管理客户的权限");
//        }
//        // 删除操作
//        boolean success = clientService.removeById(clientId);
//        if(success){
//            return R.ok();
//        }else {
//            return R.error("删除客户信息成功");
//        }
//    }
//
//    /**
//     * 添加客户信息
//     * 权限：root、clientAdmin
//     * @param client
//     * @return
//     */
//    @PostMapping
//    public R addClients(@RequestBody @Validated Client client){
//        // 权限验证
//        if(!authorityForClient()){
//            return R.error("没有管理客户的权限");
//        }
//        // 增加操作
//        boolean success = clientService.save(client);
//        if(success){
//            return R.ok();
//        }else {
//            return R.error("添加客户信息成功");
//        }
//    }
//
//    /**
//     * 用客户id取信息
//     * @param clientId
//     * @return
//     */
//    @GetMapping("/clientId/{clientId}")
//    public R getClientById(@PathVariable Integer clientId){
//        // 权限验证
//        if(!authorityForClient()){
//            return R.error("没有管理客户的权限");
//        }
//        // 获取
//        Client client = clientService.getById(clientId);
//        return R.data(client);
//    }
//    /**
//     * 从token中获取管理员的id验证权限
//     * @return 有该权限
//     */
//    private boolean authorityForClient(){
//        // 管理员权限验证
//        String adminRole = adminDao.getRoleByAdminId(AdminWebContext.getAdminId());
//        return adminRole == Role.ROOT.getRoleCode()
//                || adminRole == Role.CLIENT_ADMIN.getRoleCode();
//    }
}
