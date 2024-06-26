package com.itranswarp.learnjava.mapper;

import com.itranswarp.learnjava.entity.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PositionMapper
{

    @Select("SELECT * FROM Position")
    List<Position> getPositions();

    @SelectProvider(type = SQLProvider.class, method = "selectPosition")
    List<Position> selectPosition(
            @Param("filter") Position filter);


    @Insert("INSERT INTO Position (name,type,establishmentQuantity)VALUES(#{position.name},#{position.type},#{position.establishmentQuantity})")
    @Options(useGeneratedKeys = true, keyProperty = "positionID", keyColumn = "positionID")
    void insertPosition(@Param("position") Position position);

    @Update("UPDATE Position SET name=#{position.name},type=#{position.type},establishmentQuantity=#{position.establishmentQuantity} WHERE positionID=#{position.positionID}")
    void updatePosition(@Param("position") Position position);

    @Delete("DELETE FROM Position WHERE positionID = #{id}")
    void deletePosition(@Param("id") String id);
}
