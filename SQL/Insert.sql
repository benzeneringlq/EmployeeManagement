INSERT INTO
	Admin (username, password, name, email)
VALUES
	('admin', 'password', 'Jack', '1@q.com'),
	('guest1', 'password', 'Tom', '2@q.com'),
	('guest2', 'password', 'Jerry', '3@q.com');

INSERT INTO
	Messages (username, msgName, msgContext)
VALUES
	('admin', 'msgName', 'Lorem ipsum'),
	('admin', 'msgName', 'Lorem ipsum'),
	('admin', 'msgName', 'Lorem ipsum');

INSERT INTO
	Alerts (username, alertName, alertBadge)
VALUES
	('admin', 'alertName', 1),
	('admin', 'alertName', 2),
	('admin', 'alertName', 3);

INSERT INTO
	Department (name, type, TEL,fax,description,superior,foundingTime)
VALUES
	('人事部', '公司', '18888888888','88888801','认真负责','总公司','2024-6-19'),
	('开发部', '公司', '18888888888','88888801','认真负责','总公司','2024-6-19'),
	('宣传部', '公司', '18888888888','88888801','认真负责','总公司','2024-6-19'),
	('保洁部', '公司', '18888888888','88888801','认真负责','总公司','2024-6-19');

INSERT INTO
	Position (name, type, establishmentQuantity)
VALUES
	('文员', '管理',1),
    ('秘书', '管理',3),
    ('副部', '管理',7),
	('部长', '管理',15);


call AddStaff(1,1,'王维',1,'研究生','2024-6-19','2024-6-19','176','home','正式','正式员工','校园招聘','123456789012345678');
call AddStaff(1,2,'李白',1,'研究生','2024-6-19','2024-6-19','176','home','正式','正式员工','其他','123456789012345679');
call AddStaff(2,1,'杜甫',1,'研究生','2024-6-19','2024-6-19','176','home','正式','正式员工','社会招聘','123456789012345610');
call AddStaff(3,1,'白居易',1,'研究生','2024-6-19','2024-6-19','176','home','正式','临时员工','校园招聘','123456789012345611');
call AddStaff(1,1,'韩愈',1,'研究生','2024-6-19','2024-6-19','176','home','正式','正式员工','校园招聘','123456789012345612');
call AddStaff(1,1,'李清照',1,'研究生','2024-6-19','2024-6-19','176','home','正式','正式员工','校园招聘','123456789012345613');

call AddProbation(1,1,'屈原',1,'研究生','2024-6-19','2024-6-19','176','home','临时员工','校园招聘','123456789012345611','2024-6-19','2024-6-19');
call AddProbation(1,1,'孔子',1,'研究生','2024-6-19','2024-6-19','176','home','临时员工','校园招聘','123456789012345611','2024-6-19','2024-6-19');
call AddProbation(1,1,'新增',1,'研究生','2024-6-19','2024-6-19','176','home','临时员工','校园招聘','123456789012345611','2024-6-19','2024-6-19');


# 调部门和岗位
call changeDepartmentAndPosition (1,1,2,'吃饭');
call changeDepartmentAndPosition (2,2,2,'吃饭');
call changeDepartmentAndPosition (3,1,2,'吃饭');

# 离职
call ProcessResignation(1,'高就');
call ProcessResignation(2,'高就');
call ProcessResignation(3,'高就');