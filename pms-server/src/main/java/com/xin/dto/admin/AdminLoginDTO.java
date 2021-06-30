package com.xin.dto.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴泽欣
 * @since 2021/1/7 19:52
 */
@Data
public class AdminLoginDTO {
    /**
     * 管理员名
     */
    @ApiModelProperty(value = "管理员名", dataType = "String")
    private String adminName;

    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码", dataType = "String")
    private String adminPassword;
}
