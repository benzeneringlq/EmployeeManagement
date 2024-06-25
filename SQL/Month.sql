--  现在的人数-本月入职+本月离职-本月调入+本月调出

-- SELECT COUNT(Staff.staffID)-COUNT(Department.departmentID) FROM Staff Department
-- 本月入职
-- SELECT COUNT(*) FROM Staff WHERE joinDate>'2024-6-24'
-- 本月离职
--   SELECT COUNT(*) FROM Staff WHERE joinDate>'2024-6-24' and status='离职'
-- 本月调入
--   SELECT COUNT(*) FROM DeptTransForm WHERE transFormDate>'2024-6-24' and newdepartmentID=#{departmentID}
-- 本月调出
--   SELECT COUNT(*) FROM DeptTransForm WHERE transFormDate>'2024-6-24' and olddepartmentID=#{departmentID}

-- SELECT * FROM Admin;
-- SELECT * FROM messages;
-- SELECT * FROM alerts;
-- SELECT * FROM Department;
--  SELECT
--   Staff.name StaffName,
--   Staff.departmentID,
--   Department.name departmentName,
--   Staff.PositionID,
--   Position.name
-- FROM
--   Staff
--   INNER JOIN Department ON Staff.departmentID = Department.departmentID
--   INNER JOIN Position ON Staff.PositionID = Position.PositionID
-- SHOW TABLE STATUS WHERE Name = 'EmployeeManagement';
-- SELECT name FROM Position;
-- call AddStaff(1,1,"name",0,"本科","0012-02-02","2024-02-03","正式","校园招聘","123");
-- SELECT departmentID , name FROM Department;
-- SELECT  p.*
-- FROM Probation p
-- INNER JOIN Staff  s
-- ON s.staffID = p.staffID
-- WHERE s.positionID=2
-- and p.probationStaffID=2
-- AND p.startDate<"2025-8-8"
-- AND p.endDate<"2024-05-28";
-- INSERT INTO
-- 	DimForm ( staffID,name, departmentName,positionName,dimDate,cause)
-- VALUES
-- 	(1,"离职者","人事部","文员","2024-6-19","吃饭");
-- INSERT INTO Position (name,type,establishmentQuantity)VALUES("name","管理","2")
-- UPDATE Position SET NAME="520",type="520" , establishmentQuantity ="520" WHERE positionID=11;
-- CALL ProcessResignation(1,"吃饭");
-- call changeDepartmentAndPosition (1,1,2,"吃饭");
-- call changeDepartmentAndPosition (2,2,2,"吃饭");
-- call changeDepartmentAndPosition (3,1,2,"吃饭");
-- SELECT Staff.status FROM Probation INNER JOIN Staff ON Probation.staffID = Staff.staffID
SELECT
    Department.departmentID,

    COUNT(Staff.staffID) ,
    - COALESCE(SUM(CASE WHEN Staff.joinDate > '2024-6-24' AND Staff.joinDate < '2024-6-24' AND Staff.departmentID=Department.departmentID THEN 1 ELSE 0 END), 0)
        + COALESCE(SUM(CASE WHEN Staff.joinDate > '2024-6-24' AND Staff.status = '离职' THEN 1 ELSE 0 END), 0)
        - COALESCE(SUM(CASE WHEN DeptTransForm.transFormDate > '2024-6-24' AND  DeptTransForm.transFormDate < '2024-6-24' AND DeptTransForm.newdepartmentID = Staff.departmentID THEN 1 ELSE 0 END), 0)
        + COALESCE(SUM(CASE WHEN DeptTransForm.transFormDate > '2024-6-24' AND  DeptTransForm.transFormDate < '2024-6-24' AND DeptTransForm.olddepartmentID = Staff.departmentID THEN 1 ELSE 0 END), 0)
                                                                                                                                                                                                  AS beginMonth,

    (SELECT COUNT(*) FROM Staff WHERE  joinDate< '2024-6-24' AND Staff.departmentID=Department.departmentID)  AS endMonth,

    COALESCE(SUM(CASE WHEN Staff.joinDate > '2024-6-24' AND Staff.joinDate < '2024-6-24' AND Staff.departmentID=Department.departmentID THEN 1 ELSE 0 END), 0) AS newEntry,

    COALESCE(SUM(CASE WHEN Staff.joinDate > '2024-6-24' AND Staff.joinDate < '2024-6-24' AND Staff.status = '离职' AND Staff.departmentID=Department.departmentID THEN 1 ELSE 0 END), 0) AS dim,

    COALESCE(SUM(CASE WHEN DeptTransForm.transFormDate > '2024-6-24' AND  DeptTransForm.transFormDate < '2024-6-24' AND DeptTransForm.newdepartmentID = Staff.departmentID THEN 1 ELSE 0 END), 0) AS 'in' ,

    COALESCE(SUM(CASE WHEN DeptTransForm.transFormDate > '2024-6-24' AND  DeptTransForm.transFormDate < '2024-6-24' AND DeptTransForm.olddepartmentID = Staff.departmentID THEN 1 ELSE 0 END), 0) AS 'out' ,

    COALESCE(SUM(CASE WHEN Staff.degree = '研究生' AND Staff.departmentID=Department.departmentID THEN 1 ELSE 0 END), 0) AS master,

    COALESCE(SUM(CASE WHEN Staff.degree = '本科' AND Staff.departmentID=Department.departmentID THEN 1 ELSE 0 END), 0) AS graduate,

    COALESCE(SUM(CASE WHEN Staff.degree = '高中' AND Staff.departmentID=Department.departmentID THEN 1 ELSE 0 END), 0) AS highSchool

FROM
    Staff
        INNER JOIN Department ON Staff.departmentID = Department.departmentID
        LEFT JOIN DeptTransForm ON Staff.departmentID = DeptTransForm.olddepartmentID OR Staff.departmentID = DeptTransForm.newdepartmentID
GROUP BY
    Department.departmentID;