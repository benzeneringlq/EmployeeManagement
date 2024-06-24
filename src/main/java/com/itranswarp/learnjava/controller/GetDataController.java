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






    @PostMapping(value = "/getdata")
    public ResponseEntity<String> getData(@RequestBody String table) {

        System.out.println("Post /data/getdata!!" + table);
        try {
            String resulString = getDataService.getDataByTable(table);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/getIDNameBytable")
    public ResponseEntity<String> getIDNameBytable(@RequestBody String table) {
        try {
            String resulString = getDataService.getIdNameByTable(table);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }

}
