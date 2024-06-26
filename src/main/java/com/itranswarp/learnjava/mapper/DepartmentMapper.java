package com.itranswarp.learnjava.mapper;

import com.itranswarp.learnjava.entity.Department;
import org.apache.ibatis.annotations.*;

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

    @Insert("INSERT INTO Department (name,type,TEL,fax,description,superior,foundingTime)VALUES(#{department.name},#{department.type},#{department.TEL},#{department.fax},#{department.description},#{department.superior},NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "departmentID", keyColumn = "departmentID")
    void insertDepartment(@Param("department") Department department);

    @Update("UPDATE Department SET name=#{department.name}, type=#{department.type}, TEL=#{department.TEL}, fax=#{department.fax}, description=#{department.description}, superior=#{department.superior}, foundingTime=#{department.foundingTime} WHERE departmentID=#{department.departmentID}")
    void updateDepartment(@Param("department") Department department);

    @Delete("DELETE FROM Department WHERE departmentID = #{id}")
    void deleteDepartment(@Param("id") String id);

}
