package com.xin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_admin]对应实体类
 * 
 * @author 欣 2021年01月15日 
 */
@Data
@TableName(value = "`t_admin`")
@ApiModel(value = "表[t_admin]的实体类")
public class Admin {

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "int")
    @TableField("`admin_id`")
    private int adminId;

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "String")
    @TableField("`admin_name`")
    private String adminName;

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "String")
    @TableField("`admin_role`")
    private String adminRole;

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "String")
    @TableField("`admin_password`")
    private String adminPassword;

}