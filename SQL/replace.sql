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
           , 1, 0)), 0) AS 'master',
       COALESCE(SUM(IF(Staff.degree = '本科'
           , 1, 0)), 0) AS graduate,
       COALESCE(SUM(IF(Staff.degree = '高中'
           , 1, 0)), 0) AS highSchool
FROM Staff
         INNER JOIN DeptTransForm ON Staff.departmentID = DeptTransForm.oldID
         LEFT JOIN NewStaffForm ON Staff.staffID = NewStaffForm.staffID
         LEFT JOIN DimForm ON Staff.staffID = DimForm.staffID
GROUP BY Staff.departmentID;

	
	
	
	