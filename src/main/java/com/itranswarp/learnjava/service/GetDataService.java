package com.itranswarp.learnjava.service;

import java.util.List;

import com.itranswarp.learnjava.mapper.DepartmentMapper;
import com.itranswarp.learnjava.mapper.PositionMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.Department;
import com.itranswarp.learnjava.entity.NameID;
import com.itranswarp.learnjava.entity.Position;
import com.itranswarp.learnjava.mapper.GetDataMapper;

@Service
@Transactional
public class GetDataService {
    // 注入GetDataMapper:
    @Autowired
    private GetDataMapper getDataMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private Gson gson;

    /**
     * 从数据库拿月报的Service方法
     */
    public String getMonthlyForm() {
        return "success";
    }




    public String getDataByTable(String table) {
        if (table.equals("Position")) {
            List<Position> positions = positionMapper.getPositions();
            String json = gson.toJson(positions);
            return json;
        } else if (table.equals("Department")) {
            List<Department> departments = departmentMapper.getDepartments();
            String json = gson.toJson(departments);
            return json;
        } else {
            // Handle other cases or throw an exception if the table is not recognized
            throw new IllegalArgumentException("Maybe Unknown table: " + table + "from GetDataService.java");
        }
    }

    public String getIdNameByTable(String table) {
        System.out.println(table);
        List<NameID> names = getDataMapper.getIDNameByTable(table, table.toLowerCase());
        String json = gson.toJson(names);
        return json;

    }
}