package com.xin.dto.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;

/**
 * @author 吴泽欣
 * @since 2021/1/5 16:00
 */
@Data
public class LoginDTO {

    @ApiModelProperty(value = "邮箱号", dataType = "String", required = true)
    @Pattern(regexp ="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式错误")
    private String email;

    @ApiModelProperty(value = "密码", dataType = "String", required = true)
    @Pattern(regexp = "^[A-Za-z0-9$!?@#%^&]{6,20}$",message = "密码格式错误")
    private String password;

    @ApiModelProperty(value = "验证码", dataType = "String", required = true)
    @Pattern(regexp = "^[0-9a-z]{4,8}$",message = "验证码格式错误")
    private String verificationCode;

}
