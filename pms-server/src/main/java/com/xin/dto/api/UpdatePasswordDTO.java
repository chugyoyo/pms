package com.xin.dto.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

/**
 * @author 吴泽欣
 * @since 2021/1/13 15:22
 */
@Data
public class UpdatePasswordDTO {

    @ApiModelProperty(value = "旧密码", dataType = "String")
    @Pattern(regexp = "^[A-Za-z0-9$!?@#%^&]{6,20}$",message = "密码格式错误")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", dataType = "String")
    @Pattern(regexp = "^[A-Za-z0-9$!?@#%^&]{6,20}$",message = "密码格式错误")
    private String newPassword;

}
