package com.itranswarp.learnjava.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itranswarp.learnjava.service.DimissionService;

@RestController
@RequestMapping(value = "/dimission", produces = "application/json;charset=UTF-8")
public class DimissionController {
    @Autowired
    DimissionService dimissionService;

    @PostMapping(value = "/selectDimission")
    public ResponseEntity<String> selectDimission(@RequestBody String json) {

        try {
            String resulString = dimissionService.selectDimission(json);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }



}
