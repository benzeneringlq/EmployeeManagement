package com.itranswarp.learnjava.mapper;

import com.itranswarp.learnjava.entity.Position;
import com.itranswarp.learnjava.entity.Probation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.itranswarp.learnjava.entity.Staff;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public class SQLProvider {

    public String selectPosition(
            @Param("filter") Position filter) {
        return new SQL() {
            {
                SELECT("*");
                FROM("Position");
                if (filter.positionID != null && !filter.positionID.isEmpty()) {
                    WHERE("positionID = #{filter.positionID}");
                }
                if (filter.name != null && !filter.name.isEmpty()) {
                    WHERE("name = #{filter.name}");
                }
                if (filter.type != null && !filter.type.isEmpty()) {
                    WHERE("type = #{filter.type}");
                }
            }
        }.toString();
    }

    public String selectDepartment(
            @Param("id") String id,
            @Param("name") String name,
            @Param("type") String type) {
        return new SQL() {
            {
                SELECT("*");
                FROM("Department");
                if (!"".equals(id)) {
                    WHERE("departmentID = #{id}");
                }
                if (!"".equals(name)) {
                    WHERE("name = #{name}");
                }
                if (!"".equals(type)) {
                    WHERE("type = #{type}");
                }
            }
        }.toString();
    }

    public String selectDimission(
            @Param("staffID") String staffID,
            @Param("name") String name,
            @Param("departmentName") String departmentName,
            @Param("positionName") String positionName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("cause") String cause) {
        return new SQL() {
            {
                SELECT("*");
                FROM("DimForm");
                if (staffID != null && !staffID.isEmpty()) {
                    WHERE("staffID = #{staffID}");
                }
                if (name != null && !name.isEmpty()) {
                    WHERE("name = #{name}");
                }
                if (departmentName != null && !departmentName.isEmpty()) {
                    WHERE("departmentName = #{departmentName}");
                }
                if (positionName != null && !positionName.isEmpty()) {
                    WHERE("positionName = #{positionName}");
                }
                if (startDate != null && !startDate.isEmpty()) {
                    WHERE("dimDate >= #{startDate}");
                }
                if (endDate != null && !endDate.isEmpty()) {
                    WHERE("dimDate <= #{endDate}");
                }
                if (cause != null && !cause.isEmpty()) {
                    WHERE("cause = #{cause}");
                }
            }
        }.toString();
    }

    public String selectStaff(
            @Param("staffFilter") Staff staffFilter) {
        return new SQL() {
            {
                SELECT("*");
                FROM("Staff");
                if (staffFilter.name != null && !staffFilter.name.isEmpty()) {
                    WHERE("name = #{staffFilter.name}");
                }
                if (staffFilter.staffID != null && !staffFilter.staffID.isEmpty()) {
                    WHERE("staffID = #{staffFilter.staffID}");
                }
                if (staffFilter.departmentID != null && !staffFilter.departmentID.isEmpty()) {
                    WHERE("departmentID = #{staffFilter.departmentID}");
                }
                if (staffFilter.positionID != null && !staffFilter.positionID.isEmpty()) {
                    WHERE("positionID = #{staffFilter.positionID}");
                }
            }
        }.toString();
    }

    public String selectProbation(
            @Param("filter") Probation filter) {
        return new SQL() {
            {
                SELECT("Probation.*,Staff.name,Staff.DepartmentID,Staff.status");
                FROM("Probation");
                JOIN("Staff ON Staff.staffID = Probation.staffID");
                if (filter.name != null && !filter.name.isEmpty()) {
                    WHERE("Staff.name = #{filter.name}");
                }
                if (filter.probationStaffID != null && !filter.probationStaffID.isEmpty()) {
                    WHERE("Probation.probationStaffID = #{filter.probationStaffID}");
                }
                if (filter.departmentID != null && !filter.departmentID.isEmpty()) {
                    WHERE("Staff.departmentID = #{filter.departmentID}");
                }
                if (filter.positionID != null && !filter.positionID.isEmpty()) {
                    WHERE("Staff.positionID = #{filter.positionID}");
                }
                if (filter.status != null && !filter.status.isEmpty()) {
                    WHERE("Staff.status = #{filter.status}");
                }
                if (filter.startDate != null && !filter.startDate.isEmpty()) {
                    WHERE("Probation.startDate = #{filter.startDate}");
                }
                if (filter.endDate != null && !filter.endDate.isEmpty()) {
                    WHERE("Probation.endDate = #{filter.endDate}");
                }
            }
        }.toString();
    }

    public String getData(
            @Param("table") String table) {
        return new SQL() {
            {
                SELECT("*");
                FROM("#{table}");
            }
        }.toString();
    }
}
