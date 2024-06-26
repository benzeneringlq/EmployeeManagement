package com.itranswarp.learnjava.controller;

import com.itranswarp.learnjava.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/department",produces = "application/json;charset=UTF-8")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @PostMapping(value = "/selectDepartment")
    public ResponseEntity<String> selectDepartment(@RequestBody String filter) {
        try {
            String resulString = departmentService.selectDepartment(filter);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }
    /**
     * 删除Department
     */
    @PostMapping(value = "/deleteDepartment")
    public ResponseEntity<String> deleteDepartment(@RequestBody String data) {
        try {
            String resulString = departmentService.deleteDepartment(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }
    /**
     * 更新Department
     */
    @PostMapping(value = "/updateDepartment")
    public ResponseEntity<String> updateDepartment(@RequestBody String data) {
        try {
            String resulString = departmentService.updateDepartment(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }
    /**
     * 插入Department
     */
    @PostMapping(value = "/insertDepartment")
    public ResponseEntity<String> insertDepartment(@RequestBody String data) {
        try {
            String resulString = departmentService.insertDepartment(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }

}
