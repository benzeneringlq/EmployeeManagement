-- 储存过程，添加员工-----------------------------------------------------------
DROP PROCEDURE
    IF
        EXISTS AddStaff;

DELIMITER //
CREATE PROCEDURE AddStaff(
    IN p_departmentID INT,
    IN p_positionID INT,
    IN p_name VARCHAR(30),
    IN p_gender BOOLEAN,
    IN p_degree VARCHAR(20),
    IN p_joinDate DATE,
    IN p_workStartDate DATE,
    IN p_TEL VARCHAR(30),
    IN p_home VARCHAR(30),
    IN p_status VARCHAR(30),
    IN p_employmentType VARCHAR(20),
    IN p_source VARCHAR(20),
    IN p_idNumber VARCHAR(20)
)
BEGIN
    DECLARE
        p_newStaffID INT;

    -- 插入Staff表
    INSERT INTO Staff (departmentID, positionID, name, gender, degree, joinDate, workStartDate, TEL, home, status,
                       employmentType, source, idNumber)
    VALUES (p_departmentID, p_positionID, p_name, p_gender, p_degree, NOW(), p_workStartDate, p_TEL, p_home,
            p_status, p_employmentType, p_source, p_idNumber);
    SELECT LAST_INSERT_ID()
    INTO p_newStaffID;
    INSERT INTO NewStaffForm(staffID, jointDate) VALUE (p_newStaffID, NOW());
    -- 获取刚插入的员工ID
--     SELECT *
--     FROM Staff
--     WHERE staffID = LAST_INSERT_ID();

END //
DELIMITER ;
-- 储存过程，添加实习和员工-----------------------------------------------------------
DROP PROCEDURE
    IF
        EXISTS AddProbation;

DELIMITER //
CREATE PROCEDURE AddProbation(
    IN p_departmentID INT,
    IN p_positionID INT,
    IN p_name VARCHAR(30),
    IN p_gender BOOLEAN,
    IN p_degree VARCHAR(20),
    IN p_joinDate DATE,
    IN p_workStartDate DATE,
    IN p_TEL VARCHAR(30),
    IN p_home VARCHAR(30),
    IN p_employmentType VARCHAR(20),
    IN p_source VARCHAR(20),
    IN p_idNumber VARCHAR(20),
    IN p_startDate DATE,
    IN p_endDate DATE
)
BEGIN
    DECLARE
        p_newStaffID INT;
    -- 插入Staff表
    INSERT INTO Staff (departmentID, positionID, name, gender, degree, joinDate, workStartDate, TEL, home, status,
                       employmentType, source, idNumber)
    VALUES (p_departmentID, p_positionID, p_name, p_gender, p_degree, NOW(), p_workStartDate, p_TEL, p_home,
            '实习', p_employmentType, p_source, p_idNumber);
    -- 获取刚插入的员工ID
    SELECT LAST_INSERT_ID()
    INTO p_newStaffID;
    -- 插入新员工表
    INSERT INTO NewStaffForm(staffID, jointDate) VALUE (p_newStaffID, NOW());
    -- 插入Probation表
    INSERT INTO Probation (staffID, startDate, endDate)
    VALUES (p_newStaffID, p_startDate, p_endDate);

END //
DELIMITER ;
-- 定时检测实习生转正---------------------------------------------------------------------------------------
DROP PROCEDURE
    IF
        EXISTS DeleteExpiredProbation;

DELIMITER //
CREATE PROCEDURE DeleteExpiredProbation()
BEGIN
    DELETE
    FROM Probation
    WHERE endDate < CURDATE();

END //
DELIMITER ;
DROP EVENT
    IF
        EXISTS AutoDeleteExpiredProbation;
CREATE EVENT AutoDeleteExpiredProbation ON SCHEDULE EVERY 1 DAY DO
    CALL DeleteExpiredProbation();

-- 离职 -------------------------------------------------------------------------------------
DROP PROCEDURE
    IF
        EXISTS ProcessResignation;

DELIMITER //
CREATE PROCEDURE ProcessResignation(
    IN p_staffID INT,
    p_cause VARCHAR(30))
BEGIN
    DECLARE
        p_name VARCHAR(30);
    DECLARE
        p_departmentName VARCHAR(30);
    DECLARE
        p_positionName VARCHAR(30);
    SELECT Staff.name,
           Department.name,
           Position.name
    INTO p_name,
        p_departmentName,
        p_positionName
    FROM Staff
             INNER JOIN Department ON Staff.departmentID = Department.departmentID
             INNER JOIN Position ON Staff.positionID = Position.positionID
    WHERE Staff.staffID = p_staffID;
    INSERT INTO DimForm (staffID, name, departmentName, positionName, dimDate, cause)
    VALUES (p_staffID, p_name, p_departmentName, p_positionName, NOW(), p_cause);
-- 更新Staff表中记录为离职
    UPDATE Staff SET status='离职'
    WHERE Staff.staffID=p_staffID;
-- 删除Probation表中的记录
    DELETE
    FROM Probation
    WHERE staffID = p_staffID;

END //
DELIMITER ;
-- 更改部门和岗位 -------------------------------------------------------------------------------------
DROP PROCEDURE
    IF
        EXISTS changeDepartmentAndPosition;

DELIMITER //
CREATE PROCEDURE changeDepartmentAndPosition(IN p_staffID INT, IN p_departmentID INT, IN p_positionID INT,
                                             IN p_cause VARCHAR(30))
BEGIN
    DECLARE
        v_oldDepartmentID INT;
    DECLARE
        v_oldpositionID INT;
    DECLARE
        v_currentDate DATE;
    -- Get the current department and position of the staff
    SELECT departmentID,
           positionID
    INTO v_oldDepartmentID,
        v_oldpositionID
    FROM Staff
    WHERE staffID = p_staffID;
    -- Get the current date

    SET v_currentDate = CURDATE();
    -- Update the department if it's different
    IF
        v_oldDepartmentID != p_departmentID THEN
        UPDATE Staff
        SET departmentID = p_departmentID
        WHERE staffID = p_staffID;
        -- Insert into DeptTransForm
        INSERT INTO DeptTransForm (staffID, oldID, newID, date, cause)
        VALUES (p_staffID, v_oldDepartmentID, p_departmentID, v_currentDate, p_cause);

    END IF;
    -- Update the position if it's different
    IF
        v_oldpositionID != p_positionID THEN
        UPDATE Staff
        SET positionID = p_positionID
        WHERE staffID = p_staffID;
-- Insert into PositionTransForm
        INSERT INTO PositionTransForm (staffID, oldID, newID, date, cause)
        VALUES (p_staffID, v_oldpositionID, p_positionID, v_currentDate, p_cause);

    END IF;

END //
DELIMITER ;

-- 更改实习生 -------------------------------------------------------------------------------------
DROP PROCEDURE
    IF
        EXISTS changeProbation;

DELIMITER //

CREATE PROCEDURE changeProbation(
    IN p_probationStaffID INT,
    IN p_change INT,
    IN p_toDate DATE
)
BEGIN
    DECLARE p_staffID INT;
    -- 转正：删除对应的Probation行
    IF p_change = 1 THEN
        DELETE
        FROM Probation
        WHERE probationStaffID = p_probationStaffID;

        -- 延期：修改对应行的endDate
    ELSEIF p_change = 2 THEN
        UPDATE Probation
        SET endDate = p_toDate
        WHERE probationStaffID = p_probationStaffID;

        -- 不通过：删除对应的Probation行，并调用ProcessResignation存储过程
    ELSEIF p_change = 0 THEN
        -- 获取 staffID 以便调用 ProcessResignation

        SELECT staffID
        INTO p_staffID
        FROM Probation
        WHERE probationStaffID = p_probationStaffID;

        -- 删除对应的Probation行
        DELETE
        FROM Probation
        WHERE probationStaffID = p_probationStaffID;

        -- 调用ProcessResignation存储过程
        CALL ProcessResignation(p_staffID, '试用期未通过');
    END IF;
END //

DELIMITER ;

