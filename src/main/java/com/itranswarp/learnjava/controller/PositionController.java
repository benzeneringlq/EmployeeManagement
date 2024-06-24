package com.itranswarp.learnjava.controller;

import com.itranswarp.learnjava.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/position", produces = "application/json;charset=UTF-8")
public class PositionController {
    @Autowired
    PositionService positionService;
    /**
     * 删除Position
     */
    @PostMapping(value = "/deletePosition")
    public ResponseEntity<String> deletePosition(@RequestBody String data) {
        try {
            String resulString = positionService.deletePosition(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }
    /**
     * 更新Position
     */
    @PostMapping(value = "/updatePosition")
    public ResponseEntity<String> updatePosition(@RequestBody String data) {
        try {
            String resulString = positionService.updatePosition(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }
    /**
     * 插入Position
     */
    @PostMapping(value = "/insertPosition")
    public ResponseEntity<String> insertPosition(@RequestBody String data) {
        try {
            String resulString = positionService.insertPosition(data);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }

    /**
     * 查询Position
     */
    @PostMapping(value = "/selectPosition")
    public ResponseEntity<String> selectPosition(@RequestBody String filter) {
        try {
            String resulString = positionService.selectPosition(filter);
            return ResponseEntity.ok(resulString);
        } catch (Exception e) {
            return MyFunction.createErrorResponse("error", e);
        }

    }

}
