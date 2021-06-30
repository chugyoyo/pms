package com.xin.dto.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴泽欣
 * @since 2021/1/8 19:27
 */
@Data
public class AdminDTO {
    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id", dataType = "int")
    private int adminId;

    /**
     * 管理员名
     */
    @ApiModelProperty(value = "管理员名", dataType = "String")
    private String adminName;

    /**
     * 管理员角色
     */
    @ApiModelProperty(value = "管理员角色", dataType = "String")
    private String adminRole;
}
