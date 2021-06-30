package com.xin.dto.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author 吴泽欣
 * @since 2021/1/14 19:49
 */
@Data
public class EmailDTO {

    @ApiModelProperty(value = "邮箱号", dataType = "String", required = true)
    @Pattern(regexp ="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式错误")
    private String email;
}
