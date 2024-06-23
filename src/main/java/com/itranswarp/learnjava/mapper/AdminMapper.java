package com.itranswarp.learnjava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itranswarp.learnjava.entity.Alert;

@Mapper
public interface AdminMapper {
        @Select("SELECT alertName,alertBadge,viewed FROM Alerts WHERE username = #{username}")
        List<Alert> getAlertByUsername(@Param("username") String username);

}
