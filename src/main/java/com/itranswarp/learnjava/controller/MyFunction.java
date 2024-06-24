package com.itranswarp.learnjava.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class MyFunction {
    // 错误处理函数
    static  public ResponseEntity<String> createErrorResponse(String errorCode, Exception e) {
        JSONObject errorJson = new JSONObject();
        errorJson.put("success", false);
        errorJson.put(errorCode, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson.toString());
    }
}
