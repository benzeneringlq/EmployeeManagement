package com.itranswarp.learnjava.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.itranswarp.learnjava.entity.Probation;

@Mapper
public interface ProbationMapper {

    @Select("SELECT status FROM Probation")
    List<Probation> getProbationstatus();

    @SelectProvider(type = SQLProvider.class, method = "selectProbation")
    List<Probation> selectProbation(
            @Param("name") String name,
            @Param("probationStaffID") String probationStaffID,
            @Param("departmentID") String departmentID,
            @Param("positionID") String positionID,
            @Param("status") String status,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}
