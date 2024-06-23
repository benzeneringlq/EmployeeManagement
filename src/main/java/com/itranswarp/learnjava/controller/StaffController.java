package com.itranswarp.learnjava.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itranswarp.learnjava.service.StaffService;

@RestController
@RequestMapping(value = "/staff", produces = "application/json;charset=UTF-8")
public class StaffController {


    @Autowired
    StaffService staffService;


    
    @PostMapping(value = "/entryStaff")
    public ResponseEntity<String> entryStaff(@RequestBody String json) {

        try {
            String resulString = staffService.entryStaff(json);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/selectStaff")
    public ResponseEntity<String> selectStaff(@RequestBody String filter) {
        try {
            String resulString = staffService.selectStaff(filter);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }
    }


    // 错误处理函数
    private ResponseEntity<String> createErrorResponse(String errorCode, Exception e) {
        JSONObject errorJson = new JSONObject();
        errorJson.put("success", false);
        errorJson.put(errorCode, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson.toString());
    }
}
