package com.example.tpwsmartparking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tpwsmartparking.entity.ParkingLot;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParkingLotMapper {

    //查询所有停车场信息
    @Select("select * from parkinglot")
    List<ParkingLot> getParkingLotList();

    //删除停车场
    @Delete("DELETE FROM `parkinglot` WHERE `parking_id` = #{parkingId}")
    Integer delParkingLot(Integer parkingId);

    //添加停车场
    @Insert("INSERT INTO parkinglot(parking_id, parking_name,parking_space_count,timing_unit,unit_cost) VALUES(#{parkingId},#{parkingName},#{parkingSpaceCount},#{timingUnit},#{unitCost})")
    int insertParkingLot(@Param("parkingId") Integer parkingId, @Param("parkingName") String parkingName, @Param("parkingSpaceCount") Integer parkingSpaceCount
            , @Param("timingUnit") Integer timingUnit, @Param("unitCost") Integer unitCost);
    //修改停车场数据
    @Update("update parkinglot set parking_id=#{parkingId}, parking_name=#{parkingName},parking_space_count=#{parkingSpaceCount}, timing_unit=#{timingUnit} ,unit_cost=#{unitCost} where parking_id=#{parkingId} ")
    Integer updataParkingLot(@Param("parkingId") Integer parkingId, @Param("parkingName") String parkingName, @Param("parkingSpaceCount") Integer parkingSpaceCount
            , @Param("timingUnit") Integer timingUnit, @Param("unitCost") Integer unitCost);

    //根据车场名寻找车场
    @Select("select * from parkinglot where parking_name = #{parkingName}")
    ParkingLot getParkingLotByName(String parkingName);

}

