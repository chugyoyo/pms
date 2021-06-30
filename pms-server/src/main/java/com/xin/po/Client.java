package com.xin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_client]对应实体类
 * 存在的用户才会被查询到
 * @author 欣 2021年01月05日 
 */

@Data
@TableName(value = "`v_client_exists`")
@ApiModel(value = "视图[v_client_exists]的实体类")
public class Client {

    /**
     * 用户号
     */
    @ApiModelProperty(value = "用户号", dataType = "int")
    @TableField("`client_id`")
    @TableId(value = "`client_id`",type = IdType.AUTO)
    private int clientId;

    /**
     * 用户名（不能重复）
     */
    @ApiModelProperty(value = "用户名", dataType = "String")
    @TableField("`client_name`")
    private String clientName;

    /**
     * 用户邮箱号（不能重复）
     */
    @ApiModelProperty(value = "用户邮箱号", dataType = "String")
    @TableField("`client_email`")
    private String clientEmail;

    /**
     * 用户账号创建时间
     */
    @ApiModelProperty(value = "用户账号创建时间", dataType = "java.util.Date")
    @TableField("`client_created_time`")
    private java.util.Date clientCreatedTime;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", dataType = "String")
    @TableField("`client_password`")
    private String clientPassword;

    /**
     * 用户账号的状态
     */
    @ApiModelProperty(value = "用户账号的状态", dataType = "String")
    @TableField("`client_status`")
    private String clientStatus;

}