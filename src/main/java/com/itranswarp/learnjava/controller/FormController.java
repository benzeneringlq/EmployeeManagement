package com.itranswarp.learnjava.controller;

import com.itranswarp.learnjava.service.FormService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/form", produces = "application/json;charset=UTF-8")
public class FormController {

    @Autowired
    FormService formService;

    /**
     * 新员工报表
     */
    @PostMapping("/getNewForm")
    public ResponseEntity<String> getNewForm(@RequestBody String number) {
        try {
            String resulString = formService.getNewForm();
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }

    /**
     * 离职员工报表
     */
    @PostMapping("/getDimForm")
    public ResponseEntity<String> getDimForm(@RequestBody String number) {
        try {
            String resulString = formService.getDimForm();
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }

    /**
     * 岗位调整
     */
    @PostMapping("/getPosTransForm")
    public ResponseEntity<String> getPosTransForm(@RequestBody String number) {
        try {
            String resulString = formService.getPosTransForm();
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }

    /**
     * 部门调整
     */
    @PostMapping("/getDepTransForm")
    public ResponseEntity<String> getDepTransForm(@RequestBody String number) {
        try {
            String resulString = formService.getDepTransForm();
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }

    /**
     * 月报
     */
    @PostMapping("/getMonthlyForm")
    public ResponseEntity<String> getMonthlyForm(@RequestBody String number) {
        try {
            String resulString = formService.getMonthlyForm();
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }

}
