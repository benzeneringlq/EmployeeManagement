package com.itranswarp.learnjava.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.Probation;
import com.itranswarp.learnjava.entity.Staff;
import com.itranswarp.learnjava.mapper.StaffMapper;

@Service
@Transactional
public class StaffService {

    // 注入UserMapper:
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private Gson gson;



    public String entryStaff(String json) {
        Staff staff = gson.fromJson(json, Staff.class);
        staffMapper.entryStaff(staff);
        return "success";
    }

    public String selectStaff(String filter) {
        JSONObject filterObject = new JSONObject(filter);
        // System.out.println(json);
        // System.out.println("staffID is:" + jsonObject.optString("staffID", null) + "|
        // from:selectStaff(String json) {");
        // System.out.println("name is:" + jsonObject.optString("name", null) + "|
        // from:selectStaff(String json) {");
        List<Staff> staffs = staffMapper.select(
                filterObject.optString("name", ""),
                filterObject.optString("staffID", ""),
                filterObject.optString("departmentID", ""),
                filterObject.optString("positionID", ""));

        String responsejson = gson.toJson(staffs);
        return responsejson;
    }

    public String entryProbation(String json) {
        Probation probation = gson.fromJson(json, Probation.class);
        String resulString = staffMapper.entryProbation(probation);
        return resulString;
    }

    public String deleteStaff(String json) {
        JSONObject jsonObject = new JSONObject(json);
        List<Staff> staffs = staffMapper.select(
                jsonObject.getString("staffName"),
                jsonObject.getString("staffID"),
                jsonObject.getString("departmentName"),
                jsonObject.getString("positionName"));

        String responsejson = gson.toJson(staffs);
        return responsejson;
    }
}