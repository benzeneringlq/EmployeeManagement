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
    public String updatePosition(String data) {
        Position position = gson.fromJson(data, Position.class);
        positionMapper.updatePosition(position);
        return position.getID();

    }

    /**
     * 插入岗位的Service 方法
     */
    public String insertPosition(String data) {
        Position position = gson.fromJson(data, Position.class);
        positionMapper.insertPosition(position);
        return position.getID();

    }

    /**
     * 查询岗位的Service 方法
     */
    public String selectPosition(String filter) {
        JSONObject filterObject = new JSONObject(filter);
        List<Position> positions = positionMapper.selectPosition(
                filterObject.optString("id", ""),
                filterObject.optString("name", ""),
                filterObject.optString("type", ""));
        String json = gson.toJson(positions);
        return json;

    }
}
