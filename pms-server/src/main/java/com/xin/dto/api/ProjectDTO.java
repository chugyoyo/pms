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
 * @since 2021/1/11 0:26
 */
@Data
public class ProjectDTO {
    /**
     * 项目号
     */
    @ApiModelProperty(value = "项目号", dataType = "int")
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

    /**
     * 项目超级负责人名
     */
    @ApiModelProperty(value = "项目超级负责人名", dataType = "String")
    @Pattern(regexp = "^[\u4e00-\u9fa5a-zA-Z\\d]{3,10}$",message = "用户名格式错误")
    private String projectRootName;

    /**
     * 邀请码
     */
    @ApiModelProperty(value = "邀请码", dataType = "String")
    @Pattern(regexp = "^[a-z0-9]{32}$",message = "邀请码格式错误")
    private String projectCode;

    /**
     * 项目创建时间
     */
    @ApiModelProperty(value = "项目创建时间", dataType = "java.util.Date")
//    @Pattern(regexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$",message="时间格式错误")
    private java.util.Date projectCreatedTime;
}
