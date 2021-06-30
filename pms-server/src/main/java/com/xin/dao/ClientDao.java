package com.xin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.po.Client;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 表[t_client]对应实体类
 * 
 * @author 欣 2021年01月05日 
 */
public interface ClientDao extends BaseMapper<Client> {

    @Update("update t_client " +
            "set client_name=#{clientName} " +
            "where client_id = #{clientId}")
    Boolean updateClientName(@Param("clientId") Integer clientId,@Param("clientName") String clientName);

    @Select("SELECT IFNULL((SELECT true from t_client where client_name = #{clientName}),false)")
    Boolean existsClientName(String clientName);

    @Select("SELECT IFNULL((SELECT true from t_client where client_email = #{clientEmail}),false)")
    Boolean existsClientEmail(String clientEmail);

    @Select("select client_id from t_client where client_email=#{clientEmail}")
    Integer getClientIdByEmail(String clientEmail);

    @Update("update t_client " +
            "set client_password=#{password} " +
            "where client_id=#{clientId}")
    Boolean updatePasswordByClientId(Integer clientId,String password);

    Page<Client> pageSearch(@Param("keywords") String keywords, @Param("pg") Page page);
}