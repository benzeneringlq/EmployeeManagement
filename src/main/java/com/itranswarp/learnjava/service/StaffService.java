package com.itranswarp.learnjava.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
        Staff staffFilter = gson.fromJson(filter, Staff.class);
        List<Staff> staffs = staffMapper.select(staffFilter);

        return gson.toJson(staffs);
    }

    public String deleteStaff(String id) {
        staffMapper.deleteStaff(id);
        return id;
    }
    public String dimStaff(String id) {
        staffMapper.dimStaff(id);
        return id;
    }

    public String updateStaff(String json) {
        Staff staff = gson.fromJson(json, Staff.class);
        staffMapper.updateStaff(staff);
        return "success";
    }

    public String changeDepartmentAndPosition(String json) {
        JSONObject jsonObject = new JSONObject(json);
        staffMapper.changeDepartmentAndPosition(
                jsonObject.getString("staffID"),
                jsonObject.getString("departmentID"),
                jsonObject.getString("positionID"),
                jsonObject.getString("cause"));
        return "success";
    }
}