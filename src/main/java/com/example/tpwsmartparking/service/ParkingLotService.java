package com.example.tpwsmartparking.service;

import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.vo.ParkingLotVo;

//停车场管理
public interface ParkingLotService {
    ParkingLotVo<ParkingLot> getParkingLotVo(Integer page, Integer limit);

    Integer deleteParkingLotList(String parkingIdList);

    String insertParkingLot(Integer parkingId, String parkingName, Integer parkingSpaceCount, Integer timingUnit, Integer unitCost);

    Integer updataParkingLot(Integer parkingId, String parkingName, Integer parkingSpaceCount, Integer timingUnit, Integer unitCost);

    void delParkingLot(int parseInt);

    ParkingLot getParkingLotByName(String parkingName);

}
