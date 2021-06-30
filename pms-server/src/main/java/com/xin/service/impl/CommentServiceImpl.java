package com.xin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.dao.CommentDao;
import com.xin.po.Comment;
import com.xin.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * Comment对应service实现
 * 
 * @author 欣 2021年01月05日 
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao,Comment> implements CommentService {

}