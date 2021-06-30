package com.xin.dto.api;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author 吴泽欣
 * @since 2021/1/5 17:18
 */
@Data
public class CreateClientDTO {

    @ApiModelProperty(value = "邮箱号", dataType = "String", required = true)
    @Pattern(regexp ="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式错误")
    private String email;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", dataType = "String")
    @Pattern(regexp = "^[\u4e00-\u9fa5a-zA-Z\\d]{3,10}$",message = "用户名格式错误")
    private String name;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", dataType = "String")
    @Pattern(regexp = "^[A-Za-z0-9$!?@#%^&]{6,20}$",message = "密码格式错误")
    private String password;

    /**
     * 邮箱验证码
     */
    @ApiModelProperty(value = "邮箱验证码", dataType = "String")
    @Pattern(regexp = "^[0-9]{4,8}$",message = "验证码格式错误")
    private String emailCode;
}
