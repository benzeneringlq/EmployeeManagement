-- 计算月 -------------------------------------------------------------------------------------
DROP PROCEDURE
    IF
        EXISTS ProcessResignation;

DELIMITER //
CREATE PROCEDURE ProcessResignation(
    IN p_staffID INT,
    p_cause VARCHAR(30))
BEGIN
    DECLARE
        p_beginMonth INT;
    DECLARE
        p_endMonth INT;
    DECLARE
        p_new_entry INT;
    DECLARE
        p_new_din INT;
    DECLARE
        p_new_in INT;
    DECLARE
        p_new_out INT;

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