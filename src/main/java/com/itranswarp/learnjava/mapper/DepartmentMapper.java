package com.itranswarp.learnjava.mapper;

import com.itranswarp.learnjava.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM Department")
    List<Department> getDepartments();

    @SelectProvider(type = SQLProvider.class, method = "selectDepartment")
    List<Department> selectDepartment(
            @Param("id") String id,
            @Param("name") String name,
            @Param("type") String type);

}
