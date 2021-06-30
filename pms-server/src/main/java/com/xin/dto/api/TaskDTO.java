package com.xin.dto.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author 吴泽欣
 * @since 2021/1/7 10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    /**
     * 任务号
     */
    @ApiModelProperty(value = "任务号", dataType = "int",required = true)
    // 这里因为是更新和新建的操作都用到了此方法
//    @Min(value = 1,message = "id格式错误")
    private int taskId;

    /**
     * 任务状态
     */
    @ApiModelProperty(value = "任务状态", dataType = "String")
    @Pattern(regexp = "^[A-Z_]{3,20}$",message = "任务状态格式错误")
    private String taskStatus;

    /**
     * 任务状态名
     */
    @ApiModelProperty(value = "任务状态名", dataType = "String")
    @Pattern(regexp = "^[\u4e00-\u9fa5]{1,10}$",message = "任务状态名格式错误")
    private String taskStatusName;

    /**
     * 任务的负责人
     */
    @ApiModelProperty(value = "任务的负责人", dataType = "int")
    @Min(value = 0,message = "id格式错误")
    private int taskPrinciple;

    /**
     * 任务的负责人名
     */
    @ApiModelProperty(value = "任务的负责人名", dataType = "String")
    @Pattern(regexp = "^[\u4e00-\u9fa5a-zA-Z\\d]{3,10}$",message = "用户名格式错误")
    private String taskPrincipleName;

    /**
     * 任务所在项目
     */
    @ApiModelProperty(value = "任务所在项目", dataType = "int")
    @Min(value = 1,message = "id格式错误")
    private int taskProject;

    /**
     * 任务截止时间
     */
    @ApiModelProperty(value = "任务截止时间", dataType = "java.util.Date")
    private java.util.Date taskDeadline;

    /**
     * 任务的完成时间
     */
    @ApiModelProperty(value = "任务的完成时间", dataType = "java.util.Date")
    private java.util.Date taskFinishedTime;

    /**
     * 任务名
     */
    @ApiModelProperty(value = "任务名", dataType = "String")
    @Pattern(regexp = "^[\u4e00-\u9fa5a-zA-Z\\d]{1,20}$",message = "标题格式错误")
    private String taskName;

    /**
     * 任务详情
     */
    @ApiModelProperty(value = "任务详情", dataType = "String")
    @Size(max = 255,message = "详情信息格式错误")
    private String taskDetail;
}
