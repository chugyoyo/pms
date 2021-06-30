package com.xin.controller.api;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.dao.ClientProjectDao;
import com.xin.dao.ProjectDao;
import com.xin.dto.api.CreateProjectDTO;
import com.xin.dto.api.ProjectDTO;
import com.xin.dto.api.UpdateProjectDTO;
import com.xin.po.ClientProject;
import com.xin.po.Project;
import com.xin.service.ClientProjectService;
import com.xin.service.ProjectService;
import com.xin.web.WebContext;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 项目的操作
 * 大部分的项目操作只有项目管理员才能做
 *
 * @author 吴泽欣
 * @since 2021/1/6 20:08
 */
@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Resource
    private ProjectDao projectDao;

    @Resource
    private ClientProjectService clientProjectService;

    @Resource
    private ClientProjectDao clientProjectDao;

    /**
     * 通过存着的clientId去取得所有项目
     *
     * @return
     */
    @GetMapping("/all")
    public R getAllProjects() {
        int clientId = WebContext.getClientId();
        List<Project> projects = projectDao.getAllProjectsByClientId(clientId);
        return R.data(projects);
    }

    /**
     * 根据项目id查看相关信息
     * @param projectId
     * @return
     */
    @GetMapping("/{projectId}")
    public R getProjectInfo(@PathVariable Integer projectId){
        int clientId = WebContext.getClientId();
        if(!clientProjectDao.clientInProject(clientId,projectId)){
            return R.error("客户不在该项目中");
        }
        return R.data(projectService.getById(projectId));
    }

    // 幂等性？？

    /**
     * 根据传过来的项目信息
     * 经过验证后
     * 更新项目信息
     * 可以提供的功能：更新项目信息、移交管理员
     *
     * @param updateProjectDTO
     * @return
     */
    @PutMapping
    public R updateProject(@RequestBody @Validated UpdateProjectDTO updateProjectDTO) {
        int projectId = updateProjectDTO.getProjectId();
        int clientId = WebContext.getClientId();
        // 不存在此项目
        Project oldProject = projectService.getById(projectId);
        if (oldProject == null) {
            return R.error("项目不存在");
        }
        // 验证是否有更新的权限
        if (clientId != oldProject.getProjectRoot()) {
            return R.error("您不是该项目的管理员");
        }
        // 验证是否移交的项目管理员在此项目中
        if (!clientProjectDao.clientInProject(updateProjectDTO.getProjectRoot(), projectId)) {
            return R.error("项目管理员不能交给不在项目中的人");
        }
        // 更新操作
        Project newProject = new Project();
        newProject.setProjectId(projectId);
        newProject.setProjectDescription(updateProjectDTO.getProjectDescription());
        newProject.setProjectRoot(updateProjectDTO.getProjectRoot());
        newProject.setProjectTitle(updateProjectDTO.getProjectTitle());
        newProject.setProjectCreatedTime(oldProject.getProjectCreatedTime());
        boolean success = projectService.updateById(newProject);
        if (success) {
            return R.ok();
        } else {
            return R.error("更新项目信息失败");
        }
    }

    /**
     * 新建项目
     *
     * @return
     */
    @PostMapping
    public R newProject(@RequestBody @Validated CreateProjectDTO createProjectDTO) {
        Project project = new Project();
        project.setProjectTitle(createProjectDTO.getProjectTitle());
        project.setProjectDescription(createProjectDTO.getProjectDescription());
        int clientId = WebContext.getClientId();
        // 已被拦截，id不会是0
        project.setProjectRoot(clientId);
        // 邀请码生成
        project.setProjectCode(SecureUtil.md5(clientId + UUID.randomUUID().toString()));
        log.info("clientId=" + clientId + " create project=" + project);
        boolean success = projectService.save(project);
        // 后台触发子实现对t_client_project表的插入
        if (success) {
            return R.ok();
        } else {
            return R.error("新建项目失败");
        }
    }

    /**
     * 根据code和token中的clientId加入项目
     *
     * @param code
     * @return
     */
    @PostMapping("/into")
    public R intoProject(@RequestParam("projectCode") String code) {
        log.info(code);
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("project_code", code);
        Project project = projectService.getOne(wrapper);
        // 邀请码是否可以找到项目
        if (project == null) {
            return R.error("邀请码错误");
        }
        // 验证客户是否已经在项目中
        if (clientProjectDao.clientInProject(WebContext.getClientId(), project.getProjectId())) {
            return R.error("已在项目中，无需再加入项目");
        }
        // 开始加入项目
        ClientProject clientProject = new ClientProject();
        clientProject.setClientId(WebContext.getClientId());
        clientProject.setProjectId(project.getProjectId());
        boolean success = clientProjectService.save(clientProject);
        if (success) {
            return R.ok();
        } else {
            return R.error("由于某些不可控因素，加入项目失败");
        }
    }

    /**
     * 用户退出项目
     *
     * @param projectId
     * @return
     */
    @DeleteMapping("/leave/{projectId}")
    public R leaveProject(@PathVariable("projectId") Integer projectId) {
        int clientId = WebContext.getClientId();
        // 用户不在此项目中
        if (!clientProjectDao.clientInProject(clientId, projectId)) {
            return R.error("用户原本就不在此项目中");
        }
        // 用户是项目的管理员
        if (projectDao.getRootByProjectId(projectId) == clientId) {
            return R.error("项目的管理员不能退出项目");
        }
        // 退出项目
        // 由于前面验证了，肯定是成功的
        boolean success = clientProjectDao.removeByClientIdAndProjectId(clientId, projectId);
        if (success) {
            return R.ok();
        } else {
            return R.error("由于不可控因素，删除失败");
        }
    }

    /**
     * 删除项目
     *
     * @param projectId
     * @return
     */
    @DeleteMapping("/{projectId}")
    public R deleteProject(@PathVariable("projectId") Integer projectId) {
        int clientId = WebContext.getClientId();
        // 用户不是项目的管理员
        if (projectDao.getRootByProjectId(projectId) != clientId) {
            return R.error("不是项目的管理员不能删除项目");
        }
        // 删除项目
        // 因为有触发子会删除项目客户关系表的相关记录
        boolean success = projectService.removeById(projectId);
        if (success) {
            return R.ok();
        } else {
            return R.error("由于不可控因素，删除失败");
        }
    }

    /**
     * 根据token信息取得用户的项目表（分页）
     * @return
     */
    @GetMapping("/list")
    public R getProjectList(Page page){
        int clientId = WebContext.getClientId();
        Page<ProjectDTO> projectPage = projectDao.pageSearch(clientId, page);
        return R.data(projectPage);
    }

    /**
     * 查找项目的成员
     * @param projectId
     * @return
     */
    @GetMapping("/members/{projectId}")
    public R getMembersByProjectId(@PathVariable Integer projectId){
        if(!clientProjectDao.clientInProject(WebContext.getClientId(),projectId)){
            return R.error("客户不在项目中");
        }
        // 获取所有成员信息
        return R.data(clientProjectDao.getAllClientInfosByProjectId(projectId));
    }

}
