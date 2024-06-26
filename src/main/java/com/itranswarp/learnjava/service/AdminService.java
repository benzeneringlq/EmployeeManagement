package com.itranswarp.learnjava.service;

import java.util.List;

import com.itranswarp.learnjava.entity.Admin;
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

    public String getAlert(String username) {
        List<Alert> alerts = adminMapper.getAlertByUsername(username);
        return gson.toJson(alerts);
    }

    public String login(String json) {
        Admin admin = gson.fromJson(json, Admin.class);
        int userNum = adminMapper.login(admin);

        if (userNum == 1) {
            return "success";
        } else {
            return "用户名或密码错误";
        }
    }

    public String register(String json) {
        Admin admin = gson.fromJson(json, Admin.class);
        int userNum = adminMapper.login(admin);
        if (userNum != 0)
            return "用户名已存在";
        adminMapper.register(admin);
        if (admin.getID() == null) {
            return "fail";
        } else {
            return "success";
        }
    }

}