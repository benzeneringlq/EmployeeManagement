package com.itranswarp.learnjava.entity;
import java.sql.Timestamp;

public class Department {
    String departmentID;
    String name;
    String type;
    String TEL;
    String fax;
    String description;
    String superior;
    Timestamp foundingTime;

    public String getID() {
        return departmentID;
    }
}
