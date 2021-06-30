package com.xin.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.dao.ClientProjectDao;
import com.xin.dao.TaskDao;
import com.xin.dto.api.TaskDTO;
import com.xin.enums.TaskStatus;
import com.xin.po.Task;
import com.xin.service.ClientService;
import com.xin.service.TaskService;
import com.xin.web.WebContext;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目的任务的操作
 * 大部分操作项目成员都能参与
 *
 * @author 吴泽欣
 * @since 2021/1/7 10:43
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @Resource
    private TaskDao taskDao;

    @Resource
    private ClientProjectDao clientProjectDao;

    @Resource
    private ClientService clientService;


    /**
     * 根据项目id和token中的用户id取得所有任务
     *
     * @return
     */
    @GetMapping("/list")
    public R getAllTasks(Integer projectId, Page page) {
        // 不是项目的成员
        if (!clientProjectDao.clientInProject(WebContext.getClientId(), projectId)) {
            return R.error("您不是该项目的成员,不能获取项目的所有任务！");
        }
        // 获取任务
        Page<TaskDTO> taskPage = taskDao.pageSearch(projectId,page);
        return R.data(taskPage);
    }

    /**
     * 更新任务
     * 包含功能：更新任务详情、完成任务、新建任务
     *
     * @param taskDTO
     * @return
     */
    @PostMapping
    public R addOrUpdateTask(@RequestBody @Validated TaskDTO taskDTO) {
        boolean isNew;
        Task task = taskService.getById(taskDTO.getTaskId());
        if (task == null) {
            // 任务不存在
            isNew = true;
            task = new Task();
        } else {
            // 任务已存在
            isNew = false;
            // 任务所在的项目号发生变化
            if (task.getTaskProject() != taskDTO.getTaskProject()) {
                return R.error("任务不能更换所在项目！");
            }
        }
        int projectId = taskDTO.getTaskProject();
        // 不是项目的成员
        if (!clientProjectDao.clientInProject(WebContext.getClientId(), projectId)) {
            return R.error("您不是该项目的成员,不能更新或创建任务！");
        }
        // 任务负责人不是项目的人
        if (!clientProjectDao.clientInProject(taskDTO.getTaskPrinciple(), projectId)) {
            return R.error("任务负责人不是项目的人！");
        }
        // 完成时间和截止时间和状态仍需改进？？？？？
        task.setTaskDeadline(taskDTO.getTaskDeadline());
        task.setTaskDetail(taskDTO.getTaskDetail());
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskPrinciple(taskDTO.getTaskPrinciple());
        if(!isNew){
            task.setTaskStatus(taskDTO.getTaskStatus());
        }else {
            task.setTaskStatus(TaskStatus.UNFINISHED.getCode());
        }
        task.setTaskFinishedTime(taskDTO.getTaskFinishedTime());
        task.setTaskProject(projectId);
        boolean success;
        if (isNew) {
            log.info("save task(忽略taskId) => {}", task);
            success = taskService.save(task);
        } else {
            log.info("update task => {}", task);
            success = taskService.updateById(task);
        }
        if (success) {
            return R.ok();
        } else {
            return R.error("新建任务失败");
        }
    }

    @GetMapping("/{taskId}")
    public R getTaskByTaskId(@PathVariable Integer taskId){
        if(!taskDao.clientInTask(WebContext.getClientId(),taskId)){
            return R.error("客户不在此任务所在的项目中");
        }
        Task task = taskService.getById(taskId);
        return R.data(task);
    }

    /**
     * 根据任务id删除任务
     *
     * @param taskId
     * @return
     */
    @DeleteMapping("/{taskId}")
    public R deleteTask(@PathVariable("taskId") Integer taskId) {
        Task task = taskService.getById(taskId);
        if (task == null) {
            return R.error("该任务不存在");
        }
        int projectId = task.getTaskProject();
        boolean exists = clientProjectDao.clientInProject(WebContext.getClientId(), projectId);
        if (!exists) {
            return R.error("用户不能删除自身项目之外的任务！");
        }
        boolean success = taskService.removeById(taskId);
        if (success) {
            return R.ok();
        } else {
            return R.error("删除任务失败！");
        }
    }
}
