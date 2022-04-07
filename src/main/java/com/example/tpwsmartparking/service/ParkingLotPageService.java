package com.example.tpwsmartparking.service;

import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.vo.ParkingLotVo;

public interface ParkingLotPageService {
    ParkingLotVo<ParkingLot> getParkingLotVo(Integer page, Integer limit);
}
