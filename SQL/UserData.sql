DROP DATABASE IF EXISTS EmployeeManagement;

CREATE DATABASE EmployeeManagement;

USE EmployeeManagement;

CREATE TABLE
	Admin (
	    adminID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		username VARCHAR(30) NOT NULL,
		password VARCHAR(30) NOT NULL,
		email VARCHAR(30)
	);

CREATE TABLE
	Messages (
		id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
        adminID int NOT NULL,
		msgName VARCHAR(30) NOT NULL,
		msgDate DATETIME NOT NULL DEFAULT NOW(),
		msgContext VARCHAR(100) NOT NULL,
		isRead BOOLEAN NOT NULL DEFAULT FALSE,
		FOREIGN KEY (adminID) REFERENCES Admin (adminID)
	);

CREATE TABLE
	Alerts (
		id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		adminID int NOT NULL,
		alertName VARCHAR(30) NOT NULL,
		alertBadge ENUM (
			'default',
			'primary',
			'success',
			'info',
			'warning',
			'danger'
		) NOT NULL,
		isView BOOLEAN NOT NULL DEFAULT FALSE,
		FOREIGN KEY (adminID) REFERENCES Admin (adminID)
	);

CREATE TABLE
	Department (
		departmentID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		name VARCHAR(30),
		type VARCHAR(30),
		TEL VARCHAR(30),
		fax VARCHAR(30),
		description VARCHAR(30),
		superior VARCHAR(30),
		foundingTime DATE
	);

CREATE TABLE
	Position (
		positionID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		name VARCHAR(30),
		type VARCHAR(30),
		establishmentQuantity INT
	);

CREATE TABLE
	Staff (
		staffID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		departmentID int NOT NULL,
		positionID int NOT NULL,
		name VARCHAR(30),
		gender BOOLEAN,
		degree VARCHAR(20),
		joinDate DATE,
		workStartDate DATE,
		TEL VARCHAR(30),
		home VARCHAR(30),
		status VARCHAR(20) DEFAULT '正常',
		employmentType VARCHAR(20),
		source VARCHAR(20),
		idNumber VARCHAR(20),
		FOREIGN KEY (departmentID) REFERENCES Department (departmentID),
		FOREIGN KEY (positionID) REFERENCES Position (positionID)
	);

CREATE TABLE
	Probation (
		probationStaffID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		staffID int NOT NULL,
		startDate DATE,
		endDate DATE,
		FOREIGN KEY (staffID) REFERENCES Staff (staffID)
	);

-- -------------------------------
CREATE TABLE
	NewStaffForm (
		formID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		staffID int,
		jointDate DATE,
		FOREIGN KEY (staffID) REFERENCES Staff (staffID)
	);

CREATE TABLE
	DimForm (
		formID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		staffID int,
		name VARCHAR(30),
		departmentName VARCHAR(30),
		positionName VARCHAR(30),
		dimDate DATE,
		cause VARCHAR(50),
        FOREIGN KEY (staffID) REFERENCES Staff (staffID)
	);

CREATE TABLE
	DeptTransForm (
		formID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		staffID int,
		oldID int,
		newID int,
		date date,
		cause VARCHAR(50),
		FOREIGN KEY (staffID) REFERENCES Staff (staffID),
		FOREIGN KEY (oldID) REFERENCES Department (departmentID),
		FOREIGN KEY (newID) REFERENCES Department (departmentID)
	);

CREATE TABLE
	PositionTransForm (
		formID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		staffID int,
		oldID int,
        newID int,
		date date,
		cause VARCHAR(50),
		FOREIGN KEY (staffID) REFERENCES Staff (staffID),
		FOREIGN KEY (oldID) REFERENCES Position (positionID),
		FOREIGN KEY (newID) REFERENCES Position (positionID)
	);