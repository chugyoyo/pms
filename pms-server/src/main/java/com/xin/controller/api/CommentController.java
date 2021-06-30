package com.xin.controller.api;

import com.xin.dao.ClientProjectDao;
import com.xin.dao.TaskDao;
import com.xin.po.ClientProject;
import com.xin.service.TaskService;
import com.xin.web.WebContext;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 吴泽欣
 * @since 2021/1/9 15:31
 */
@Slf4j
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

//    @Resource
//    private ClientProjectDao clientProjectDao;
//
//    @Resource
//    private TaskDao taskDao;
//
//    @PostMapping("/add")
//    public R publishComment(){
//
//    }
//
//    @GetMapping("/{taskId}")
//    public R getTaskCommentList(@PathVariable Integer taskId){
//        if(!taskDao.clientInTask(WebContext.getClientId(),taskId){
//            return R.error("客户不在该任务的项目中");
//        }
//        return R.data(taskService.getById(taskId));
//    }
}
