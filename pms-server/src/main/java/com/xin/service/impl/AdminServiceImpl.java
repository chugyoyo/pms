package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.AdminDao;
import com.xin.po.Admin;
import com.xin.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * Admin对应service实现
 * 
 * @author 欣 2021年01月15日 
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao,Admin> implements AdminService {

}