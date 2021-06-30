package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.OperationLogDao;
import com.xin.po.OperationLog;
import com.xin.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * OperationLog对应service实现
 * 
 * @author 欣 2021年01月05日 
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogDao,OperationLog> implements OperationLogService {

}