package com.xin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_comment]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
@Data
@TableName(value = "`t_comment`")
@ApiModel(value = "表[t_comment]的实体类")
public class Comment {

    /**
     * 评论唯一标识
     */
    @ApiModelProperty(value = "评论唯一标识", dataType = "int")
    @TableField("`comment_id`")
    @TableId(value = "`comment_id`",type = IdType.AUTO)
    private int commentId;

    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间", dataType = "java.util.Date")
    @TableField("`comment_time`")
    private java.util.Date commentTime;

    /**
     * 评论的内容
     */
    @ApiModelProperty(value = "评论的内容", dataType = "String")
    @TableField("`comment_content`")
    private String commentContent;

    /**
     * 评论的发布者
     */
    @ApiModelProperty(value = "评论的发布者", dataType = "int")
    @TableField("`comment_publisher`")
    private int commentPublisher;

    /**
     * 评论针对的任务对象
     */
    @ApiModelProperty(value = "评论针对的任务对象", dataType = "int")
    @TableField("`comment_task`")
    private int commentTask;

}