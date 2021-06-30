package com.xin.dto.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * @author 吴泽欣
 * @since 2021/1/10 13:34
 */
@Data
public class ClientInfoDTO {
    /**
     * 用户号
     */
    @ApiModelProperty(value = "用户号", dataType = "int")
    @Min(value = 1,message = "id格式错误")
    private int clientId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", dataType = "String")
    @Pattern(regexp = "^[\u4e00-\u9fa5a-zA-Z\\d]{3,10}$",message = "用户名格式错误")
    private String clientName;

    /**
     * 用户邮箱号
     */
    @ApiModelProperty(value = "用户邮箱号", dataType = "String")
    @Pattern(regexp ="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式错误")
    private String clientEmail;

}
