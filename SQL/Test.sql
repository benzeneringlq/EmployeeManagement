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
call AddStaffAndProbation(1, 1, 'test', 1, '本科'
    ,'2024-5-5', '2024-5-5', '176', 'home'
    ,'实习', '校招', 'idNumber'
    ,'2024-5-5', '2024-5-5'
     )