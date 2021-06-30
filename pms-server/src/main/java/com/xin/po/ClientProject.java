package com.xin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_client_project]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
@Data
@TableName(value = "`t_client_project`")
@ApiModel(value = "表[t_client_project]的实体类")
public class ClientProject {

    /**
     * 项目号
     */
    @ApiModelProperty(value = "项目号", dataType = "int")
    @TableField("`project_id`")
    private int projectId;

    /**
     * 用户号
     */
    @ApiModelProperty(value = "用户号", dataType = "int")
    @TableField("`client_id`")
    private int clientId;

}