package com.itranswarp.learnjava.service;

import com.google.gson.Gson;
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
        String json = gson.toJson(departments);
        return json;

    }
}
