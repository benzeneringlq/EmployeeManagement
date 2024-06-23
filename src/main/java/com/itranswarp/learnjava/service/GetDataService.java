package com.itranswarp.learnjava.service;

import java.util.List;

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
    private Gson gson;

    public String deletePosition(String id) {
        getDataMapper.deletePosition(id);
        return "success";

    }

    public String updatePosition(String data) {
        Position position = gson.fromJson(data, Position.class);
        getDataMapper.updatePosition(position);
        return position.getID();

    }

    public String insertPosition(String data) {
        Position position = gson.fromJson(data, Position.class);
        getDataMapper.insertPosition(position);
        return position.getID();

    }

    public String selectPosition(String filter) {
        JSONObject filterObject = new JSONObject(filter);
        List<Position> positions = getDataMapper.selectPosition(
                filterObject.optString("id", ""),
                filterObject.optString("name", ""),
                filterObject.optString("type", ""));
        String json = gson.toJson(positions);
        return json;

    }

    public String selectDepartment(String filter) {
        JSONObject filterObject = new JSONObject(filter);
        List<Department> departments = getDataMapper.selectDepartment(
                filterObject.optString("id", ""),
                filterObject.optString("name", ""),
                filterObject.optString("type", ""));
        String json = gson.toJson(departments);
        return json;

    }

    public String getDataByTable(String table) {
        if (table.equals("Position")) {
            List<Position> positions = getDataMapper.getPositions();
            String json = gson.toJson(positions);
            return json;
        } else if (table.equals("Department")) {
            List<Department> departments = getDataMapper.getDepartments();
            String json = gson.toJson(departments);
            return json;
        } else {
            // Handle other cases or throw an exception if the table is not recognized
            throw new IllegalArgumentException("Maybe Unknown table: " + table + "from GetDataService.java");
        }
    }

    public String getIdNameByTable(String table) {
        System.out.println(table);
        List<NameID> names = getDataMapper.getIDNameBytable(table, table.toLowerCase());
        String json = gson.toJson(names);
        return json;

    }
}