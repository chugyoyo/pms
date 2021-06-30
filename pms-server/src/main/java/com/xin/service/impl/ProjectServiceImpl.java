package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.ProjectDao;
import com.xin.po.Project;
import com.xin.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * Project对应service实现
 * 
 * @author 欣 2021年01月05日 
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao,Project> implements ProjectService {

}