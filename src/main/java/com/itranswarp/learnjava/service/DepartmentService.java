package com.itranswarp.learnjava.service;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.Department;
import com.itranswarp.learnjava.entity.Department;
import com.itranswarp.learnjava.mapper.DepartmentMapper;
import com.itranswarp.learnjava.mapper.GetDataMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private Gson gson;

    /**
     * 查询部门的Service 方法
     */
    public String selectDepartment(String filter) {
        JSONObject filterObject = new JSONObject(filter);
        List<Department> departments = departmentMapper.selectDepartment(
                filterObject.optString("id", ""),
                filterObject.optString("name", ""),
                filterObject.optString("type", ""));
        return gson.toJson(departments);

    }
    /**
     * 删除岗位的Service 方法
     */
    public String deleteDepartment(String id) {
        departmentMapper.deleteDepartment(id);
        return "success";

    }

    /**
     * 更新岗位的Service 方法
     */
    public String updateDepartment(String json) {
        Department department = gson.fromJson(json, Department.class);
        departmentMapper.updateDepartment(department);
        return department.getID();

    }

    /**
     * 插入岗位的Service 方法
     */
    public String insertDepartment(String json) {
        Department department = gson.fromJson(json, Department.class);
        departmentMapper.insertDepartment(department);
        return department.getID();

    }

}
