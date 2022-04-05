package com.example.tpwsmartparking.service;

import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.vo.ParkingRecordVo;

import java.time.LocalDateTime;

public interface ParkingRecordService {
    //检查目前的停车状态
    String selectParkingRecordExist(String imageNumber);
    //插入一条停车记录
    void insertParkingRecord(String parkingName, String imageNumber, LocalDateTime now, LocalDateTime end, int money, String status);
    //修改停车状态
    void updataParkingRecord(String parkingName, String numberPlate, LocalDateTime now, LocalDateTime end, int money, String status);
    //查询一条停车记录
    ParkingRecord getParkingRecord(String numberPlate);
    //查询所有的停车记录
    ParkingRecordVo<ParkingRecord> getParkRecordAll();
}
