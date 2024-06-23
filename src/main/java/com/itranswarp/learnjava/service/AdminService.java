package com.itranswarp.learnjava.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.Alert;
import com.itranswarp.learnjava.mapper.AdminMapper;

@Component
@Transactional
public class AdminService {
    // 注入UserMapper:
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    Gson gson;

    // public Admin getAdminById(long id) {
    //     // 调用Mapper方法:
    //     Admin user = adminMapper.getById(id);

    //     return user;
    // }

    public String getAlert(String username) {
        List<Alert> alerts = adminMapper.getAlertbyUsername(username);
        String resulString = gson.toJson(alerts);
        return resulString;
    }

    public String login(String json) {

        // Admin admin = gson.fromJson(json, Admin.class);
        JSONObject jsonObject = new JSONObject(json);
        String username = jsonObject.get("username").toString();
        // String password = jsonObject.get("password").toString();
        JSONObject responseJson = new JSONObject();
        if ("0".equals(username)) {
            responseJson.put("success", false);
        } else {
            responseJson.put("success", true);
        }
        return responseJson.toString();

    }
}