package com.xin.dto.api;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author 吴泽欣
 * @since 2021/1/6 20:24
 */
@Data
public class CreateProjectDTO {
    /**
     * 项目标题或项目名
     */
    @ApiModelProperty(value = "项目标题或项目名", dataType = "String",required = true)
    @Pattern(regexp = "^[\u4e00-\u9fa5a-zA-Z\\d]{1,20}$",message = "标题格式错误")
    private String projectTitle;

    /**
     * 项目描述信息
     */
    @ApiModelProperty(value = "项目描述信息", dataType = "String")
    @Size(max = 255,message = "详情信息格式错误")
    private String projectDescription;
}
