package com.itranswarp.learnjava.entity;

import java.sql.Timestamp;

public class Staff {
    public String staffID;
    public String departmentID;
    public String positionID = "1";
    public String name;
    public Boolean gender;
    String degree = "本科";
    Timestamp joinDate;
    Timestamp workStartDate;
    String TEL;
    String home;
    public String status;
    String employmentType;
    String source;
    public String idNumber;

    public String getIdString() {
        return String.valueOf(staffID);
    }
}
