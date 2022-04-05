package com.example.tpwsmartparking.entity;

import lombok.Data;

@Data
public class ParkingLot {
    //车场id
    private Integer parkingId;
    //车场名
    private String parkingName;
    //车场停车位
    private Integer parkingSpaceCount;
    //停车单位时间
    private Integer timingUnit;
    //停车单位费用
    private Integer unitCost;
}
