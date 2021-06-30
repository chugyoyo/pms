package com.xin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.po.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Client对应Service
 * 
 * @author 欣 2021年01月05日 
 */
public interface ClientService extends IService<Client> {

}