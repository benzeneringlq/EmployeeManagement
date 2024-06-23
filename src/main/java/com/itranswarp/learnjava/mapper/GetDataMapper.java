package com.itranswarp.learnjava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.itranswarp.learnjava.entity.Department;
import com.itranswarp.learnjava.entity.NameID;
import com.itranswarp.learnjava.entity.Position;

@Mapper
public interface GetDataMapper {

        @Select("SELECT * FROM Department")
        List<Department> getDepartments();

        @Select("SELECT * FROM Position")
        List<Position> getPositions();

        @Select("SELECT ${Lowertable}ID  as ID, name FROM ${table}")
        List<NameID> getIDNameBytable(@Param("table") String table, @Param("Lowertable") String Lowertable);

        
        @Insert("INSERT INTO Position (name,type,establishmentQuantity)VALUES(#{position.name},#{position.type},#{position.establishmentQuantity})")
        @Options(useGeneratedKeys = true, keyProperty = "positionID", keyColumn = "positionID")
        void insertPosition(@Param("position") Position position);

        @Update("UPDATE Position SET name=#{position.name},type=#{position.type},establishmentQuantity=#{position.establishmentQuantity} WHERE positionID=#{position.positionID}")
        void updatePosition(@Param("position") Position position);

        @Delete("DELETE FROM Position WHERE positionID = #{id}")
        void deletePosition(@Param("id") String id);

        @SelectProvider(type = SQLProvider.class, method = "selectDepartment")
        List<Department> selectDepartment(
                        @Param("id") String id,
                        @Param("name") String name,
                        @Param("type") String type);

        @SelectProvider(type = SQLProvider.class, method = "selectPosition")
        List<Position> selectPosition(
                        @Param("id") String id,
                        @Param("name") String name,
                        @Param("type") String type);

}
