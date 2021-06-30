package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.ClientProjectDao;
import com.xin.po.ClientProject;
import com.xin.service.ClientProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClientProject对应service实现
 * 
 * @author 欣 2021年01月05日 
 */
@Service
public class ClientProjectServiceImpl extends ServiceImpl<ClientProjectDao,ClientProject> implements ClientProjectService {

}