package com.itranswarp.learnjava.service;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.*;
import com.itranswarp.learnjava.mapper.FormMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private FormMapper formMapper;
    @Autowired
    private Gson gson;

    public String getMonthlyForm(String json) {
        FormRequestBody month = gson.fromJson(json, FormRequestBody.class);
        List<MonthlyForm> monthlyForms = formMapper.getMonthlyForm(month);
        return gson.toJson(monthlyForms);
    }

    public String getNewForm(String json) {
        FormRequestBody filter = gson.fromJson(json, FormRequestBody.class);
        List<TransForm> transForms=formMapper.getNewForm(filter);
        return gson.toJson(transForms);
    }

    public String getDimForm(String json) {
        FormRequestBody filter = gson.fromJson(json, FormRequestBody.class);
        List<TransForm> transForms=formMapper.getDimForm(filter);
        return gson.toJson(transForms);
    }

    public String getPosTransForm(String json) {
        FormRequestBody filter = gson.fromJson(json, FormRequestBody.class);
        List<TransForm> transForms=formMapper.getPosTransForm(filter);
        return gson.toJson(transForms);
    }

    public String getDepTransForm(String json) {
        FormRequestBody filter = gson.fromJson(json, FormRequestBody.class);
        List<TransForm> transForms=formMapper.getDepTransForm(filter);
        return gson.toJson(transForms);
    }


}
