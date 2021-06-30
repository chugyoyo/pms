package com.xin.dto.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴泽欣
 * @since 2021/1/8 22:49
 */
@Data
public class ClientDTO {
    /**
     * 用户号
     */
    @ApiModelProperty(value = "用户号", dataType = "int")
    private int clientId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", dataType = "String")
    private String clientName;

    /**
     * 用户邮箱号
     */
    @ApiModelProperty(value = "用户邮箱号", dataType = "String")
    private String clientEmail;

    /**
     * 用户账号的状态
     */
    @ApiModelProperty(value = "用户账号的状态", dataType = "clientStatus")
    private String clientStatus;

}
