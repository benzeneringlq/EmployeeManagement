package com.itranswarp.learnjava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.itranswarp.learnjava.entity.Dimission;

@Mapper
public interface DimissionMapper {
    @SelectProvider(type = SQLProvider.class, method = "selectDimission")
    List<Dimission> selectDimission(
            @Param("staffID") String staffID,
            @Param("name") String name,
            @Param("departmentName") String departmentName,
            @Param("positionName") String positionName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("cause") String cause);
}
