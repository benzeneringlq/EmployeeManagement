package com.itranswarp.learnjava.mapper;

import java.util.List;

import com.itranswarp.learnjava.entity.Admin;
import org.apache.ibatis.annotations.*;

import com.itranswarp.learnjava.entity.Alert;

@Mapper
public interface AdminMapper {
    @Select("SELECT alertName,alertBadge,isView FROM Alerts LEFT JOIN Admin A on A.adminID = Alerts.adminID WHERE A.username=#{username}")
    List<Alert> getAlertByUsername(@Param("username") String username);

    @Select("SELECT count(*) FROM Admin WHERE username = #{admin.username} AND password=#{admin.password}")
    int login(@Param("admin") Admin admin);

    @Insert("INSERT INTO Admin (username,password,email)VALUES(#{admin.username},#{admin.password},#{admin.email})")
    @Options(useGeneratedKeys = true, keyProperty = "adminID", keyColumn = "adminID")
    void register(@Param("admin") Admin admin);
}
