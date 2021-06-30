package com.xin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.dto.admin.ClientDTO;
import com.xin.dto.api.ClientInfoDTO;
import com.xin.dto.api.TaskDTO;
import com.xin.po.Client;
import com.xin.po.Project;
import com.xin.po.Task;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 表[t_task]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
public interface TaskDao extends BaseMapper<Task> {

    /**
     * 根据项目id来取得所有任务
     * @param projectId
     * @return
     */
    @Select("select * " +
            "from t_task " +
            "where task_project=#{projectId}")
    List<Task> getAllByProjectId(Integer projectId);

    Page<TaskDTO> pageSearch(@Param("projectId") Integer projectId, @Param("pg") Page page);

    @Select("select IFNULL((" +
            "select true " +
            "from t_client_project " +
            "where client_id = #{clientId} and project_id in (" +
            "select task_project from t_task where task_id =#{taskId}" +
            ")), false)")
    Boolean clientInTask(@Param("clientId") Integer clientId,@Param("taskId") Integer taskId);

    @Select("select t_client.* " +
            "from t_client,t_client_project,t_task " +
            "where t_client.client_id=t_client_project.client_id  " +
            "and t_client_project.project_id = t_task.task_project  " +
            "and t_task.task_id = #{taskId};")
    List<ClientInfoDTO> getClientsInProjectOfTask(Integer taskId);
}