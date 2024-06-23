package com.itranswarp.learnjava.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.Probation;
import com.itranswarp.learnjava.mapper.ProbationMapper;

@Service
@Transactional
public class ProbationService {
    // 注入GetDataMapper:
    @Autowired
    private ProbationMapper probationMapper;
    @Autowired
    private Gson gson;

    public String selectProbation(String filter) {
        JSONObject filterObject = new JSONObject(filter);
        List<Probation> probaitons = probationMapper.selectProbation(
                filterObject.optString("name", ""),
                filterObject.optString("staffID", ""),
                filterObject.optString("departmentID", ""),
                filterObject.optString("positionID", ""),
                filterObject.optString("status", ""),
                filterObject.optString("startDate", ""),
                filterObject.optString("endDate", ""));
        String responsejson = gson.toJson(probaitons);
        return responsejson;

    }

    public String getProbationstatus() {
        List<Probation> probations = probationMapper.getProbationstatus();
        return gson.toJson(probations);
    }

    public String entryProbation(String json) {
        return "TO-DO";
    }
}