package com.itranswarp.learnjava.mapper;

import com.itranswarp.learnjava.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FormMapper {
    @Select("""
            SELECT Staff.departmentID,
                                              COALESCE(SUM(IF(Staff.status!='离职', 1, 0)), 0)
                                                  - COALESCE(SUM(IF(Staff.staffID = NewStaffForm.staffID
                                                                        AND NewStaffForm.jointDate > #{month.startDate}
                                                                        AND Staff.status!='离职'
                                                  , 1, 0)), 0)
                                                  - COALESCE(SUM(IF(DeptTransForm.newID = Staff.departmentID
                                                                        AND DeptTransForm.staffID=Staff.staffID
                                                                        AND DeptTransForm.date > #{month.startDate}
                                                                        AND Staff.status!='离职'
                                                  , 1, 0)), 0)
                                                  + COALESCE(SUM(IF(DeptTransForm.oldID = Staff.departmentID
                                                                        AND DeptTransForm.staffID=Staff.staffID
                                                                        AND DeptTransForm.date > #{month.startDate}
                                                                        AND Staff.status!='离职'
                                                  , 1, 0)), 0) AS beginMonth,
                                       
                                              COALESCE(SUM(IF(Staff.status!='离职', 1, 0)), 0)
                                                  - COALESCE(SUM(IF(Staff.staffID = NewStaffForm.staffID
                                                                        AND NewStaffForm.jointDate > #{month.endDate}
                                                                        AND Staff.status!='离职'
                                                  , 1, 0)), 0)
                                                  - COALESCE(SUM(IF(DeptTransForm.newID = Staff.departmentID
                                                                        AND DeptTransForm.staffID=Staff.staffID
                                                                        AND DeptTransForm.date > #{month.endDate}
                                                                        AND Staff.status!='离职'
                                                  , 1, 0)), 0)
                                                  + COALESCE(SUM(IF(DeptTransForm.oldID = Staff.departmentID
                                                                        AND DeptTransForm.staffID=Staff.staffID
                                                                        AND DeptTransForm.date > #{month.endDate}
                                                                        AND Staff.status!='离职'
                                                  , 1, 0)), 0) AS endMonth,
                                              COALESCE(SUM(IF(Staff.staffID = NewStaffForm.staffID
                                                                  AND NewStaffForm.jointDate > #{month.startDate}
                                                                  AND NewStaffForm.jointDate < #{month.endDate}
                                                  , 1, 0)), 0) AS newEntry,
                                              COALESCE(SUM(IF(Staff.staffID = DimForm.staffID
                                                                  AND DimForm.dimDate > #{month.startDate}
                                                                  AND DimForm.dimDate < #{month.endDate}
                                                  , 1, 0)), 0) AS dim,
                                       
                                              COALESCE(SUM(IF(Staff.departmentID = DeptTransForm.newID
                                                                  And Staff.staffID=DeptTransForm.staffID
                                                                  AND DeptTransForm.date > #{month.startDate}
                                                                  AND DeptTransForm.date < #{month.endDate}
                                                  , 1, 0)), 0) AS 'in',
                                       
                                              COALESCE(SUM(IF(Staff.departmentID = DeptTransForm.oldID
                                                                  And Staff.staffID=DeptTransForm.staffID
                                                                  AND DeptTransForm.date > #{month.startDate}
                                                                  AND DeptTransForm.date < #{month.endDate}
                                                  , 1, 0)), 0) AS 'out',
                                              COALESCE(SUM(IF(Staff.degree = '研究生'
                                                                AND Staff.status!='离职'
                                                  , 1, 0)), 0) AS 'master',
                                              COALESCE(SUM(IF(Staff.degree = '本科'
                                                                AND Staff.status!='离职'
                                                  , 1, 0)), 0) AS graduate,
                                              COALESCE(SUM(IF(Staff.degree = '高中'
                                                                AND Staff.status!='离职'
                                                  , 1, 0)), 0) AS highSchool
                                       FROM Staff
                                                INNER JOIN DeptTransForm ON Staff.departmentID = DeptTransForm.oldID
                                                LEFT JOIN NewStaffForm ON Staff.staffID = NewStaffForm.staffID
                                                LEFT JOIN DimForm ON Staff.staffID = DimForm.staffID
                                       GROUP BY Staff.departmentID;
                                       
                                       	
                                       	
                                       	
                                       	""")
    List<MonthlyForm> getMonthlyForm(@Param("month") FormRequestBody month);

    @Select("SELECT NewStaffForm.formID AS 'formID', Staff.positionID AS 'newID',Staff.name AS 'name',Staff.gender AS 'gender',NewStaffForm.jointDate AS 'date',Staff.degree AS 'cause',Staff.departmentID AS 'nowDepartmentID' FROM NewStaffForm INNER JOIN Staff ON Staff.staffID=NewStaffForm.staffID")
    List<TransForm> getNewForm(FormRequestBody filter);

    @Select("SELECT DimForm.formID AS 'formID', Staff.positionID AS 'newID',Staff.name AS 'name',Staff.gender AS 'gender',DimForm.dimDate AS 'date',DimForm.cause AS 'cause',Staff.departmentID AS 'nowDepartmentID' FROM DimForm INNER JOIN Staff ON Staff.staffID=DimForm.staffID")
    List<TransForm> getDimForm(FormRequestBody filter);

    @Select("SELECT DeptTransForm.formID AS 'formID', DeptTransForm.oldID AS 'oldID',DeptTransForm.newID AS 'newID',Staff.name AS 'name',Staff.gender AS 'gender',DeptTransForm.date AS 'date',DeptTransForm.cause AS 'cause',Staff.departmentID AS 'nowDepartmentID' FROM DeptTransForm INNER JOIN Staff ON Staff.staffID=DeptTransForm.staffID")
    List<TransForm> getDepTransForm(FormRequestBody filter);

    @Select("SELECT PositionTransForm.formID AS 'formID', PositionTransForm.oldID AS 'oldID',PositionTransForm.newID AS 'newID',Staff.name AS 'name',Staff.gender AS 'gender',PositionTransForm.date AS 'date',PositionTransForm.cause AS 'cause',Staff.departmentID AS 'nowDepartmentID' FROM PositionTransForm INNER JOIN Staff ON Staff.staffID=PositionTransForm.staffID")
    List<TransForm> getPosTransForm(FormRequestBody filter);

}
