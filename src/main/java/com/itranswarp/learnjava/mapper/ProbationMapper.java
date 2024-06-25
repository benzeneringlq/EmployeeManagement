package com.itranswarp.learnjava.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.itranswarp.learnjava.entity.Probation;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface ProbationMapper {

    @Select("SELECT distinct Staff.status FROM Probation INNER JOIN Staff ON Probation.staffID = Staff.staffID")
    List<Probation> getProbationStatus();

    @SelectProvider(type = SQLProvider.class, method = "selectProbation")
    List<Probation> selectProbation(@Param("filter") Probation filter);

    @Select("CALL AddStaffAndProbation("
            + "#{probation.departmentID}, #{probation.positionID}, #{probation.name}, #{probation.gender} "
            + ",#{probation.degree}, #{probation.joinDate}, #{probation.workStartDate} "
            + ",#{probation.employmentType}, #{probation.source}, #{probation.idNumber} "
            + ",#{probation.startDate}, #{probation.endDate})")
    // @Results({
    // @Result(column = "result", property = "result", jdbcType = JdbcType.INTEGER)
    // })
    @Options(statementType = StatementType.CALLABLE)
    String entryProbation(@Param("probation") Probation probation);

}
