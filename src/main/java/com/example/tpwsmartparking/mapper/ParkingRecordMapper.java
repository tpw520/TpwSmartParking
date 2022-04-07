package com.example.tpwsmartparking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.vo.ParkingRecordVo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ParkingRecordMapper extends BaseMapper<ParkingRecord> {
    //根据字符串查询停车记录中是否存在该车
    @Select("select status  from parkingrecord where number_plate  = #{numberPlate} and status = '1'")
    String selectParkingRecordExist(@Param("numberPlate") String numberPlate);

    //插入一条停车记录
    @Insert("INSERT INTO parkingrecord(parking_name, number_plate,in_date_time,out_date_time,money,status) VALUES(#{parkingName},#{numberPlate},#{inDateTime},#{outDateTime},#{money},#{status})")
    void insertParkingRecord(@Param("parkingName") String parkingName, @Param("numberPlate") String numberPlate, @Param("inDateTime") LocalDateTime inDateTime
            , @Param("outDateTime") LocalDateTime outDateTime, @Param("money") Integer money,@Param("status") String status);

    //更新停车记录
    @Update("update parkingrecord set parking_name=#{parkingName}, number_plate=#{numberPlate},in_date_time=#{inDateTime},out_date_time=#{outDateTime}, money=#{money} ,status=#{status} where number_plate=#{numberPlate} ")
    void updataParkingRecord(@Param("parkingName") String parkingName, @Param("numberPlate")String numberPlate, @Param("inDateTime")LocalDateTime inDateTime,@Param("outDateTime") LocalDateTime outDateTime, @Param("money")int money,@Param("status") String status);

    //查询一条停车记录
    @Select("select * from parkingrecord where status = 1 and number_plate =#{numberPlate}")
    ParkingRecord getParkingRecord(String numberPlate);

    //查询所有的停车记录
    @Select("select * from parkingrecord")
    List<ParkingRecord> getParkingRecordAll();
}
