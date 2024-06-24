DROP DATABASE IF EXISTS EmployeeManagement;

CREATE DATABASE EmployeeManagement;

USE EmployeeManagement;

CREATE TABLE
	Admin (
		username VARCHAR(30) NOT NULL PRIMARY KEY,
		password VARCHAR(30) NOT NULL,
		name VARCHAR(20),
		email VARCHAR(30)
	);

CREATE TABLE
	Messages (
		id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		username VARCHAR(30) NOT NULL,
		msgName VARCHAR(30) NOT NULL,
		msgDate DATETIME NOT NULL DEFAULT NOW(),
		msgContext VARCHAR(100) NOT NULL,
		readed BOOLEAN NOT NULL DEFAULT FALSE,
		FOREIGN KEY (username) REFERENCES Admin (username)
	);

CREATE TABLE
	Alerts (
		id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		username VARCHAR(30) NOT NULL,
		alertName VARCHAR(30) NOT NULL,
		alertBadge ENUM (
			'default',
			'primary',
			'success',
			'info',
			'warning',
			'danger'
		) NOT NULL,
		viewed BOOLEAN NOT NULL DEFAULT FALSE,
		FOREIGN KEY (username) REFERENCES Admin (username)
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
		newStaffID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
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
		cause VARCHAR(50)
	);

CREATE TABLE
	DeptTransForm (
		formID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		staffID int,
		olddepartmentID int,
		newdepartmentID int,
		transFormDate date,
		cause VARCHAR(50),
		FOREIGN KEY (staffID) REFERENCES Staff (staffID),
		FOREIGN KEY (olddepartmentID) REFERENCES Department (departmentID),
		FOREIGN KEY (newdepartmentID) REFERENCES Department (departmentID)
	);

CREATE TABLE
	PositionTransForm (
		formID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
		staffID int,
		oldpositionID int,
		newpositionID int,
		transFormDate date,
		cause VARCHAR(50),
		FOREIGN KEY (staffID) REFERENCES Staff (staffID),
		FOREIGN KEY (oldpositionID) REFERENCES Position (positionID),
		FOREIGN KEY (newpositionID) REFERENCES Position (positionID)
	);