package com.itranswarp.learnjava.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.Alert;
import com.itranswarp.learnjava.entity.Dimission;
import com.itranswarp.learnjava.mapper.DimissionMapper;

@Component
@Transactional
public class DimissionService {
    @Autowired
    DimissionMapper dimissionMapper;
    @Autowired
    Gson gson;

    public String selectDimission(String filter) {
        JSONObject filterObject = new JSONObject(filter);
        List<Dimission> dimissions = dimissionMapper.selectDimission(
                filterObject.optString("staffID", ""),
                filterObject.optString("name", ""),
                filterObject.optString("departmentName", ""),
                filterObject.optString("positionName", ""),
                filterObject.optString("startDate", ""),
                filterObject.optString("endDate", ""),
                filterObject.optString("cause", ""));
        String resulString = gson.toJson(dimissions);
        return resulString;
    }
}
