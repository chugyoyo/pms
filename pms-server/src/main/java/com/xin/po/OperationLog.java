package com.xin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[t_log]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
@Data
@TableName(value = "`t_log`")
@ApiModel(value = "表[t_log]的实体类")
public class OperationLog {

    /**
     * 日志号
     */
    @ApiModelProperty(value = "日志号", dataType = "int")
    @TableField("`log_id`")
    @TableId(value = "`log_id`",type = IdType.AUTO)
    private int logId;

    /**
     * 操作者
     */
    @ApiModelProperty(value = "操作者", dataType = "int")
    @TableField("`log_client`")
    private int logClient;

    /**
     * 操作项目
     */
    @ApiModelProperty(value = "操作项目", dataType = "int")
    @TableField("`log_project`")
    private int logProject;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", dataType = "java.util.Date")
    @TableField("`log_time`")
    private java.util.Date logTime;

    /**
     * 操作的具体内容
     */
    @ApiModelProperty(value = "操作的具体内容", dataType = "String")
    @TableField("`log_detail`")
    private String logDetail;

}