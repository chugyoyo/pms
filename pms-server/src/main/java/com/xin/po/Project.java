package com.xin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_project]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
@Data
@TableName(value = "`t_project`")
@ApiModel(value = "表[t_project]的实体类")
public class Project {

    /**
     * 项目号
     */
    @ApiModelProperty(value = "项目号", dataType = "int")
    @TableField("`project_id`")
    @TableId(value = "`project_id`",type = IdType.AUTO)
    private int projectId;

    /**
     * 项目标题或项目名
     */
    @ApiModelProperty(value = "项目标题或项目名", dataType = "String")
    @TableField("`project_title`")
    private String projectTitle;

    /**
     * 项目描述信息
     */
    @ApiModelProperty(value = "项目描述信息", dataType = "String")
    @TableField("`project_description`")
    private String projectDescription;

    /**
     * 项目超级负责人 
     */
    @ApiModelProperty(value = "项目超级负责人 ", dataType = "int")
    @TableField("`project_root`")
    private int projectRoot;

    /**
     * 邀请码
     */
    @ApiModelProperty(value = "邀请码", dataType = "String")
    @TableField("`project_code`")
    private String projectCode;

    /**
     * 项目创建时间
     */
    @ApiModelProperty(value = "项目创建时间", dataType = "java.util.Date")
    @TableField("`project_created_time`")
    private java.util.Date projectCreatedTime;

}