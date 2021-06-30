package com.xin.controller.admin;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.dao.AdminDao;
import com.xin.dto.admin.AdminDTO;
import com.xin.enums.Role;
import com.xin.po.Admin;
import com.xin.service.AdminService;
import com.xin.web.AdminWebContext;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 吴泽欣
 * @since 2021/1/7 19:50
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

//    @Resource
//    private AdminService adminService;
//
//    @Resource
//    private AdminDao adminDao;
//
//    /**
//     * 新建管理员
//     *
//     * @param newAdmin
//     * @return
//     */
//    @PostMapping("/new")
//    public R newAdmin(@RequestBody @Validated Admin newAdmin) {
//        // 权限验证
//        if (!authorityForAdmin()) {
//            return R.error("只有超级管理员才能管理管理员！");
//        }
//        // 管理员名字重复
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq("admin_name", newAdmin.getAdminName());
//        if (adminService.getOne(wrapper) != null) {
//            return R.error("管理员名字重复");
//        }
//        // 不能创建超级管理员
//        if (Objects.equals(newAdmin.getAdminRole(), Role.ROOT.getRoleCode())) {
//            return R.error("不能创建超级管理员");
//        }
//        // 新建管理员
//        newAdmin.setAdminPassword(SecureUtil.md5(newAdmin.getAdminPassword()));
//        boolean success = adminService.save(newAdmin);
//        if (success) {
//            return R.ok();
//        } else {
//            return R.error("新增管理员成功");
//        }
//    }
//
//    /**
//     * 删除管理员
//     *
//     * @param adminId
//     * @return
//     */
//    @DeleteMapping("/{adminId}")
//    public R removeAdmin(@PathVariable Integer adminId) {
//        // 权限验证
//        if (!authorityForAdmin()) {
//            return R.error("只有超级管理员才能管理管理员！");
//        }
//        // 不能删除超级管理员
//        if (adminService.getById(adminId).getAdminRole() == Role.ROOT.getRoleCode()) {
//            return R.error("超级管理员不可删除");
//        }
//        // 不存在
//        if (adminService.getById(adminId) == null) {
//            return R.error("没有该管理员,删除失败");
//        }
//        boolean success = adminService.removeById(adminId);
//        if (success) {
//            return R.ok();
//        } else {
//            return R.error("不可控因素导致删除管理员失败");
//        }
//    }
//
//    /**
//     * 更新管理员信息
//     *
//     * @param newAdmin
//     * @return
//     */
//    @PostMapping("/update")
//    public R updateAdmin(@RequestBody @Validated Admin newAdmin) {
//        // 太多if不优雅？？？？？？
//        // 权限验证
//        if (!authorityForAdmin()) {
//            return R.error("只有超级管理员才能管理管理员！");
//        }
//        Admin oldAdmin = adminService.getById(newAdmin.getAdminId());
//        // 不存在该管理员
//        if (oldAdmin == null) {
//            return R.error("不存在该管理员号");
//        }
//        // 不能更新其他人为超级管理员
//        if (newAdmin.getAdminRole() == Role.ROOT.getRoleCode()) {
//            return R.error("不能更新其他人为超级管理员");
//        }
//        // 不能去除超级管理员
//        if (newAdmin.getAdminRole() != Role.ROOT.getRoleCode()
//                && oldAdmin.getAdminRole() == Role.ROOT.getRoleCode()) {
//            return R.error("不能去除超级管理员");
//        }
//        // 管理员名字与其他管理员重复
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq("admin_name", newAdmin.getAdminName());
//        wrapper.ne("admin_id", newAdmin.getAdminId());
//        if (adminService.getOne(wrapper) != null) {
//            return R.error("管理员名字与其他管理员重复了");
//        }
//        // 更新管理员
//        boolean success = adminService.updateById(newAdmin);
//        if (success) {
//            return R.ok();
//        } else {
//            return R.error("更新管理员成功");
//        }
//    }
//
//    /**
//     * 获取所有管理员信息
//     *
//     * @param keywords
//     * @param page
//     * @return
//     */
//    @GetMapping("/list")
//    public R pageSearchForAdmins(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
//        if (!authorityForAdmin()) {
//            return R.error("只有超级管理员才能管理管理员！");
//        }
//        Page<AdminDTO> pageData = adminDao.pageSearch(keywords, page);
//        return R.data(pageData);
//    }
//
//    /**
//     * 根据管理员id获取管理员信息
//     *
//     * @param adminId
//     * @return
//     */
//    @GetMapping("/adminId/{adminId}")
//    public R getAdminById(@PathVariable Integer adminId) {
//        // 权限验证
//        if (!authorityForAdmin()) {
//            return R.error("只有超级管理员才能管理管理员！");
//        }
//        // 获取管理员信息
//        Admin admin = adminService.getById(adminId);
//        if (admin == null) {
//            return R.error("找不到该管理员");
//        }
//        // 发送
//        AdminDTO adminDTO = new AdminDTO();
//        adminDTO.setAdminId(adminId);
//        adminDTO.setAdminName(admin.getAdminName());
//        adminDTO.setAdminRole(admin.getAdminRole());
//        return R.data(adminDTO);
//    }
//
//
//    /**
//     * 从token中获取管理员的id验证权限
//     *
//     * @return 有该权限
//     */
//    private boolean authorityForAdmin() {
//        return adminService.getById(AdminWebContext.getAdminId()).getAdminRole() == Role.ROOT.getRoleCode();
//    }
}
