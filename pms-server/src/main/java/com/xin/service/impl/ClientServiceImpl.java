package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.ClientDao;
import com.xin.po.Client;
import com.xin.service.ClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Client对应service实现
 * 
 * @author 欣 2021年01月05日 
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao,Client> implements ClientService {

}