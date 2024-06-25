package com.itranswarp.learnjava.service;

import com.google.gson.Gson;
import com.itranswarp.learnjava.entity.Position;
import com.itranswarp.learnjava.mapper.PositionMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    Gson gson;

    /**
     * 删除岗位的Service 方法
     */
    public String deletePosition(String id) {
        positionMapper.deletePosition(id);
        return "success";

    }

    /**
     * 更新岗位的Service 方法
     */
    public String updatePosition(String json) {
        Position position = gson.fromJson(json, Position.class);
        positionMapper.updatePosition(position);
        return position.getID();

    }

    /**
     * 插入岗位的Service 方法
     */
    public String insertPosition(String json) {
        Position position = gson.fromJson(json, Position.class);
        positionMapper.insertPosition(position);
        return position.getID();

    }

    /**
     * 查询岗位的Service 方法
     */
    public String selectPosition(String json) {
        Position filter = gson.fromJson(json, Position.class);
        List<Position> positions = positionMapper.selectPosition(filter);
        return gson.toJson(positions);

    }
}
