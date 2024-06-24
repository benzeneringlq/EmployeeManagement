-- 部门调动------------------------------------------------------------------------------------
DROP TRIGGER IF EXISTS DepartmentTrans;
DELIMITER //

CREATE TRIGGER DepartmentTrans
AFTER UPDATE ON Staff
FOR EACH ROW
BEGIN
    if OLD.departmentID neq NEW.departmentID
    insert DeptTransForm
END //

DELIMITER ;
-- 岗位调动------------------------------------------------------------------------------------
DROP TRIGGER IF EXISTS ;
DELIMITER //

CREATE TRIGGER PositionTrans
AFTER INSERT ON PositionTrans
FOR EACH ROW
BEGIN
    -- 检查是否低于60分
    IF NEW.score < 60 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = '警告：学生的成绩低于60分。';
    END IF;
END //

DELIMITER ;