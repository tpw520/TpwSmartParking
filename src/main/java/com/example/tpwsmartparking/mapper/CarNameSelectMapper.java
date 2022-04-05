package com.example.tpwsmartparking.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarNameSelectMapper {
    @Select("select parking_name from parkinglot")
    List<String> selectNameList();
}
