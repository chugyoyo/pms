package com.xin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_task]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
@Data
@TableName(value = "`t_task`")
@ApiModel(value = "表[t_task]的实体类")
public class Task {

    /**
     * 任务号
     */
    @ApiModelProperty(value = "任务号", dataType = "int")
    @TableField("`task_id`")
    @TableId(value = "`task_id`",type = IdType.AUTO)
    private int taskId;

    /**
     * 任务状态
     */
    @ApiModelProperty(value = "任务状态", dataType = "String")
    @TableField("`task_status`")
    private String taskStatus;

    /**
     * 任务的负责人
     */
    @ApiModelProperty(value = "任务的负责人", dataType = "int")
    @TableField("`task_principle`")
    private int taskPrinciple;

    /**
     * 任务所在项目
     */
    @ApiModelProperty(value = "任务所在项目", dataType = "int")
    @TableField("`task_project`")
    private int taskProject;

    /**
     * 任务截止时间
     */
    @ApiModelProperty(value = "任务截止时间", dataType = "java.util.Date")
    @TableField("`task_deadline`")
    private java.util.Date taskDeadline;

    /**
     * 任务的完成时间
     */
    @ApiModelProperty(value = "任务的完成时间", dataType = "java.util.Date")
    @TableField("`task_finished_time`")
    private java.util.Date taskFinishedTime;

    /**
     * 任务名
     */
    @ApiModelProperty(value = "任务名", dataType = "String")
    @TableField("`task_name`")
    private String taskName;

    /**
     * 任务详情
     */
    @ApiModelProperty(value = "任务详情", dataType = "String")
    @TableField("`task_detail`")
    private String taskDetail;

}