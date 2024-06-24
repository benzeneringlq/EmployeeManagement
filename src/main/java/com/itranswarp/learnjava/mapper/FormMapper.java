package com.itranswarp.learnjava.mapper;

import com.itranswarp.learnjava.entity.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FormMapper {
    @Select("SELECT * FROM Position")
    List<Position> getMonthlyForm();

    @Select("SELECT * FROM Position")
    List<Position> getNewForm();

    @Select("SELECT * FROM Position")
    List<Position> getDimForm();

    @Select("SELECT * FROM Position")
    List<Position> getPosTransForm();

    @Select("SELECT * FROM Position")
    List<Position> getDepTransForm();

}
