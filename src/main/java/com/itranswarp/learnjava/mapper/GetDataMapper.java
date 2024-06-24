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


        @Select("SELECT ${Lowertable}ID  as ID, name FROM ${table}")
        List<NameID> getIDNameByTable(@Param("table") String table, @Param("Lowertable") String Lowertable);



        @SelectProvider(type = SQLProvider.class, method = "selectDepartment")
        List<Department> selectDepartment(
                        @Param("id") String id,
                        @Param("name") String name,
                        @Param("type") String type);



}
