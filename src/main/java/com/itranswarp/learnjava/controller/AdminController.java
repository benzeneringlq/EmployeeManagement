package com.itranswarp.learnjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.servlet.ModelAndView;

// import com.google.gson.Gson;
import com.itranswarp.learnjava.service.AdminService;

// import java.util.List;

import org.json.JSONObject;

@RestController
@RequestMapping(value="/admin",produces = "application/json;charset=UTF-8")
public class AdminController {


    @Autowired
    AdminService adminService;

    @PostMapping("/getAlert")
    public ResponseEntity<String> getAlert(@RequestBody String username) {
        try {
            String resuString =adminService.getAlert(username);
            return ResponseEntity.ok(resuString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String json) {
        try {
            String resuString =adminService.login(json);
            return ResponseEntity.ok(resuString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
        
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String json) {
        try {
            String resuString =adminService.register(json);
            return ResponseEntity.ok(resuString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }



}
