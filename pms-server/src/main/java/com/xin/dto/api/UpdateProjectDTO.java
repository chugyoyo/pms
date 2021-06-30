package com.xin.dto.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author 吴泽欣
 * @since 2021/1/6 22:42
 */
@Data
public class UpdateProjectDTO {
    /**
     * 项目号
     */
    @ApiModelProperty(value = "项目号", dataType = "int",required = true)
    @Min(value = 1,message = "id格式错误")
    private int projectId;

    /**
     * 项目标题或项目名
     */
    @ApiModelProperty(value = "项目标题或项目名", dataType = "String")
    @Pattern(regexp = "^[\u4e00-\u9fa5a-zA-Z\\d]{1,20}$",message = "标题格式错误")
    private String projectTitle;

    /**
     * 项目描述信息
     */
    @ApiModelProperty(value = "项目描述信息", dataType = "String")
    @Size(max = 255,message = "详情信息格式错误")
    private String projectDescription;

    /**
     * 项目超级负责人
     */
    @ApiModelProperty(value = "项目超级负责人 ", dataType = "int")
    @Min(value = 1,message = "id格式错误")
    private int projectRoot;

}
