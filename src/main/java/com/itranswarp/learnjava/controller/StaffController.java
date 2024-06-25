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


/**
 * 增加员工
 */
    @PostMapping(value = "/entryStaff")
    public ResponseEntity<String> entryStaff(@RequestBody String json) {

        try {
            String resulString = staffService.entryStaff(json);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }
    /**
     * 查找员工
     */
    @PostMapping(value = "/selectStaff")
    public ResponseEntity<String> selectStaff(@RequestBody String filter) {
        try {
            String resulString = staffService.selectStaff(filter);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }
    /**
     * 删除员工
     */
    @PostMapping(value = "/deleteStaff")
    public ResponseEntity<String> deleteStaff(@RequestBody String id) {
        try {
            String resulString = staffService.deleteStaff(id);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }
    /**
     * 离职员工
     */
    @PostMapping(value = "/dimStaff")
    public ResponseEntity<String> dimStaff(@RequestBody String id) {
        try {
            String resulString = staffService.dimStaff(id);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }
    /**
     * 更改员工
     */
    @PostMapping(value = "/updateStaff")
    public ResponseEntity<String> updateStaff(@RequestBody String id) {
        try {
            String resulString = staffService.updateStaff(id);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }
    /**
     * 调整部门和岗位
     */
    @PostMapping(value = "/changeDepartmentAndPosition")
    public ResponseEntity<String> changeDepartmentAndPosition(@RequestBody String json) {
        try {
            String resulString = staffService.changeDepartmentAndPosition(json);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }
    }

    
}
