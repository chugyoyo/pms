package com.xin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.dto.admin.ClientDTO;
import com.xin.dto.api.ClientInfoDTO;
import com.xin.po.ClientProject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 表[t_client_project]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
public interface ClientProjectDao extends BaseMapper<ClientProject> {
    /**
     * 根据客户id查询此客户加入了哪些项目
     * @param clientId
     * @return
     */
    @Select("select project_id " +
            "from t_client_project " +
            "where client_id=#{clientId}")
    List<Integer> getAllProjectIdsByClientId(Integer clientId);
    /**
     * 根据项目id查询有哪些用户在此项目中
     * @param projectId
     * @return
     */
    @Select("select t_client.* " +
            "from t_client_project,t_client " +
            "where project_id=#{projectId} " +
            "and t_client_project.client_id=t_client.client_id")
    List<ClientInfoDTO> getAllClientInfosByProjectId(Integer projectId);

    @Delete("delete " +
            "from t_client_project " +
            "where client_id=#{clientId} and project_id=#{projectId}")
    Boolean removeByClientIdAndProjectId(@Param("clientId") Integer clientId,@Param("projectId") Integer projectId);

    /**
     * 查询项目中有多少个用户
     * @param projectId
     * @return
     */
    @Select("select count(client_id) " +
            "from t_client_project " +
            "where project_id = #{projectId}")
    Integer countClientsInProject(Integer projectId);

    /**
     * 查询用户参与了多少项目
     * @param clientId
     * @return
     */
    @Select("select count(project_id) " +
            "from t_client_project " +
            "where client_id = #{clientId}")
    Integer countProjectsForClient(Integer clientId);

    /**
     * 查询用户是否在项目中
     * @param clientId
     * @param projectId
     * @return
     */
    @Select("select IFNULL((" +
            "select true " +
            "from t_client_project " +
            "where project_id = #{projectId} and client_id = #{clientId}), false)")
    Boolean clientInProject(@Param("clientId") Integer clientId, @Param("projectId") Integer projectId);
}