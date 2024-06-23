package com.itranswarp.learnjava.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itranswarp.learnjava.service.ProbationService;

@RestController
@RequestMapping(value = "/probation", produces = "application/json;charset=UTF-8")
public class ProbationController {

    @Autowired
    ProbationService probationService;

    @PostMapping(value = "/selectProbation")
    public ResponseEntity<String> selectProbation(@RequestBody String filter) {
        System.out.println("/selectProbation"+filter);
        System.out.println("/selectProbation");
        try {
            String resulString = probationService.selectProbation(filter);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/getProbationstatus")
    public ResponseEntity<String> getProbationstatus() {

        try {
            String resulString = probationService.getProbationstatus();
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/entryProbation")
    public ResponseEntity<String> entryProbation(@RequestBody String json) {
        try {
            String resulString = probationService.entryProbation(json);
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
