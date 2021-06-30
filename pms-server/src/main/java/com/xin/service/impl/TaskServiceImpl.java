package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.TaskDao;
import com.xin.po.Task;
import com.xin.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * Task对应service实现
 * 
 * @author 欣 2021年01月05日 
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskDao,Task> implements TaskService {

}