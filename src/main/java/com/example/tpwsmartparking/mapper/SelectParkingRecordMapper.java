package com.example.tpwsmartparking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tpwsmartparking.entity.ParkingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SelectParkingRecordMapper extends BaseMapper<ParkingRecord> {
    @Select("Select * from parkingrecord where in_date_time >= #{startTime} and in_date_time <= #{endTime}")
    List<ParkingRecord> RecordExist(@Param("startTime") String startTime, @Param("endTime") String endTime) ;
    @Select("select * from parkingrecord where number_plate = #{numberPlate}")
    List<ParkingRecord> ParkRecordNumberPlate(String numberPlate);
}
