package com.itranswarp.learnjava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.StatementType;

import com.itranswarp.learnjava.entity.Admin;
import com.itranswarp.learnjava.entity.Alert;
import com.itranswarp.learnjava.entity.Probation;
import com.itranswarp.learnjava.entity.Staff;

@Mapper
public interface StaffMapper {

        @Select("SELECT name FROM users WHERE username = #{username}")
        String getUserbyUsername(@Param("username") String username);

        @Select("SELECT alertName,alertBadge,viewed FROM Alerts WHERE username = #{username}")
        List<Alert> getAlertbyUsername(@Param("username") String username);

        @Insert("INSERT INTO users name) VALUES (#{user.name})")
        void insert(@Param("user") Admin user);

        @SelectProvider(type = SQLProvider.class, method = "selectStaff")
        List<Staff> select(
                        @Param("name") String name,
                        @Param("staffID") String staffID,
                        @Param("departmentID") String departmentID,
                        @Param("positionID") String positionID);

        // + " (departmentName, positionID,
        // name,gender,degree,joinDate,workStartDate,employmentType,source,idNumber)"
        // @Insert("call EmployeeManagement.AddStaff"
        // +
        // "(#{staff.departmentName},#{staff.positionID},#{staff.name},#{staff.gender},#{staff.degree},#{staff.joinDate},#{staff.workStartDate},#{staff.employmentType}"
        // + ",#{staff.source},#{staff.idNumber})")
        @Insert("call EmployeeManagement.AddStaff"
                        + "(#{staff.departmentID},1,#{staff.name},${staff.gender},#{staff.degree},#{staff.joinDate},#{staff.workStartDate},#{staff.employmentType},#{staff.source},#{staff.idNumber})")
        @Options(statementType = StatementType.CALLABLE)
        void entryStaff(@Param("staff") Staff staff);

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
