package com.itranswarp.learnjava.entity;

import java.sql.Timestamp;
public class Staff {
    int staffID;
    int departmentID;
    int positionID=1;
    public String name;
    public Boolean gender;
    String degree="本科";
    Timestamp joinDate;
    Timestamp workStartDate;
    String employmentType;
    String source;
    public String idNumber;
    public String getIdString(){
        return String.valueOf(staffID);
    }
}
