package com.example.tpwsmartparking.service;

import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.vo.ParkingRecordVo;

public interface SelectParkingRecordService {
    ParkingRecordVo<ParkingRecord> RecordExist(String startTime, String endTime,Integer page,Integer limit);

    ParkingRecordVo<ParkingRecord> ParkRecordNumberPlate(String numberPlate, Integer page, Integer limit);
}
