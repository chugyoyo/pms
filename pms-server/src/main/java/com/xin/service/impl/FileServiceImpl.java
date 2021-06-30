package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.FileDao;
import com.xin.po.File;
import com.xin.service.FileService;
import org.springframework.stereotype.Service;

/**
 * File对应service实现
 * 
 * @author 欣 2021年01月05日 
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileDao,File> implements FileService {

}