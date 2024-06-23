package com.itranswarp.learnjava.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public class SQLProvider {

    public String selectPosition(
            @Param("id") String id,
            @Param("name") String name,
            @Param("type") String type) {
        return new SQL() {
            {
                SELECT("*");
                FROM("Position");
                if (!"".equals(id)) {
                    WHERE("positionID = #{id}");
                }
                if (!"".equals(name)) {
                    WHERE("name = #{name}");
                    // WHERE("id = " + id); 危险
                }
                if (!"".equals(type)) {
                    WHERE("type = #{type}");
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
                    // WHERE("id = " + id); 危险
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
            @Param("name") String name,
            @Param("staffID") String staffID,
            @Param("departmentID") String departmentID,
            @Param("positionID") String positionID) {
        return new SQL() {
            {
                SELECT("*");
                FROM("Staff");
                if (!"".equals(name)) {
                    WHERE("name = #{name}");
                }
                if (!"".equals(staffID)) {
                    WHERE("staffID = #{staffID}");
                    // WHERE("id = " + id); 危险
                }
                if (!"".equals(departmentID)) {
                    WHERE("departmentID = #{departmentID}");
                }
                if (!"".equals(positionID)) {
                    WHERE("positionID = #{positionID}");
                }
            }
        }.toString();
    }

    public String selectProbation(
            @Param("name") String name,
            @Param("probationStaffID") String probationStaffID,
            @Param("departmentID") String departmentID,
            @Param("positionID") String positionID,
            @Param("status") String status,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate) {
        return new SQL() {
            {
                SELECT("Probation.*,Staff.name,Staff.DepartmentID");
                FROM("Probation");
                JOIN("Staff ON Staff.staffID = Probation.staffID");
                if (name != null && !"".equals(name)) {
                    WHERE("Staff.name = #{name}");
                }
                if (probationStaffID != null && !"".equals(probationStaffID)) {
                    WHERE("Probation.probationStaffID = #{probationStaffID}");
                }
                if (departmentID != null && !"".equals(departmentID)) {
                    WHERE("Staff.departmentID = #{departmentID}");
                }
                if (positionID != null && !"".equals(positionID)) {
                    WHERE("Staff.positionID = #{positionID}");
                }
                if (status != null && !"".equals(status)) {
                    WHERE("Probation.status = #{status}");
                }
                if (startDate != null && !"".equals(startDate)) {
                    WHERE("Probation.startDate = #{startDate}");
                }
                if (endDate != null && !"".equals(endDate)) {
                    WHERE("Probation.endDate = #{endDate}");
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
