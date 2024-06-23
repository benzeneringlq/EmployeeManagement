package com.itranswarp.learnjava.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

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
