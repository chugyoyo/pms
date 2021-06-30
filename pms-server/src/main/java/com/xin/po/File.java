package com.xin.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_file]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
@Data
@TableName(value = "`t_file`")
@ApiModel(value = "表[t_file]的实体类")
public class File {

    /**
     * 文件号
     */
    @ApiModelProperty(value = "文件号", dataType = "int")
    @TableField("`file_id`")
    @TableId(value = "`file_id`",type = IdType.AUTO)
    private int fileId;

    /**
     * 文件发布时间
     */
    @ApiModelProperty(value = "文件发布时间", dataType = "java.util.Date")
    @TableField("`file_time`")
    private java.util.Date fileTime;

    /**
     * 文件发布者
     */
    @ApiModelProperty(value = "文件发布者", dataType = "int")
    @TableField("`file_publisher`")
    private int filePublisher;

    /**
     * 文件发布针对的项目
     */
    @ApiModelProperty(value = "文件发布针对的项目", dataType = "int")
    @TableField("`file_project`")
    private int fileProject;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名", dataType = "String")
    @TableField("`file_name`")
    private String fileName;

}