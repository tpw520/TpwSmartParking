package com.example.tpwsmartparking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tpwsmartparking.entity.ParkingPlate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParkingPlateMapper extends BaseMapper<ParkingPlate> {
    @Select("select * from parkingplate")
    List<ParkingPlate> getParkingPlateList();
}
