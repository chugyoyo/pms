package com.xin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.dto.admin.AdminDTO;
import com.xin.dto.api.ProjectDTO;
import com.xin.po.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 表[t_project]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
public interface ProjectDao extends BaseMapper<Project> {
    /**
     * 根据用户id查询所在的项目
     * @param clientId
     * @return
     */
    @Select("select t_project.* " +
            "from t_client_project inner join t_project " +
            "on t_client_project.project_id=t_project.project_id " +
            "where t_client_project.client_id = #{clientId}")
    List<Project> getAllProjectsByClientId(Integer clientId);

    /**
     * 根据项目号查询项目的管理员
     * @param projectId
     * @return
     */
    @Select("select project_root " +
            "from t_project " +
            "where project_id=#{projectId}")
    Integer getRootByProjectId(Integer projectId);

    Page<ProjectDTO> pageSearch(@Param("clientId") Integer clientId, @Param("pg") Page page);
}