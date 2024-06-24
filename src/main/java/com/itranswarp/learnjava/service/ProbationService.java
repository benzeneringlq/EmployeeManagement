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
//        Probation probation=Jackson
        Probation probation = gson.fromJson(filter, Probation.class);
        List<Probation> probaitons = probationMapper.selectProbation(probation);
        String responsejson = gson.toJson(probaitons);
        return responsejson;

    }

    public String getProbationStatus() {
        List<Probation> probations = probationMapper.getProbationStatus();
        return gson.toJson(probations);
    }


    public String entryProbation(String json) {
        Probation probation = gson.fromJson(json, Probation.class);
        return probationMapper.entryProbation(probation);
    }

}