package com.itranswarp.learnjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
            return MyFunction.createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/getProbationStatus")
    public ResponseEntity<String> getProbationStatus() {

        try {
            String resulString = probationService.getProbationStatus();
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }

    @PostMapping(value = "/entryProbation")
    public ResponseEntity<String> entryProbation(@RequestBody String json) {
        try {
            String resulString = probationService.entryProbation(json);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }


}
