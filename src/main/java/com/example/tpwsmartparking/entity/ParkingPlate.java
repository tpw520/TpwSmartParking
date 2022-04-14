package com.example.tpwsmartparking.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("parkingplate")
public class ParkingPlate {
    //车场名
    private String parkingName;
    //车牌号
    private String numberPlate;
    //车辆状态
    private String status;
}
