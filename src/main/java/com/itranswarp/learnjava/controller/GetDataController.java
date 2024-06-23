package com.itranswarp.learnjava.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itranswarp.learnjava.service.GetDataService;

@RestController
@RequestMapping(value = "/data", produces = "application/json;charset=UTF-8")
public class GetDataController {

    @Autowired
    GetDataService getDataService;


    @PostMapping(value = "/deletePosition")
    public ResponseEntity<String> deletePosition(@RequestBody String data) {
        try {
            String resulString = getDataService.deletePosition(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/updatePosition")
    public ResponseEntity<String> updatePosition(@RequestBody String data) {
        try {
            String resulString = getDataService.updatePosition(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }
    @PostMapping(value = "/insertPosition")
    public ResponseEntity<String> insertPosition(@RequestBody String data) {
        try {
            String resulString = getDataService.insertPosition(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }


    @PostMapping(value = "/selectPosition")
    public ResponseEntity<String> selectPosition(@RequestBody String filter) {
        try {
            String resulString = getDataService.selectPosition(filter);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/selectDepartment")
    public ResponseEntity<String> selectDepartment(@RequestBody String filter) {
        try {
            String resulString = getDataService.selectDepartment(filter);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/getdata")
    public ResponseEntity<String> getData(@RequestBody String table) {

        System.out.println("Post /data/getdata!!" + table);
        try {
            String resulString = getDataService.getDataByTable(table);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/getIDNameBytable")
    public ResponseEntity<String> getIDNameBytable(@RequestBody String table) {
        try {
            String resulString = getDataService.getIdNameByTable(table);
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
