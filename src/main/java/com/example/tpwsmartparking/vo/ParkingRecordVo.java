package com.example.tpwsmartparking.vo;

import lombok.Data;

import java.util.List;

@Data
public class ParkingRecordVo<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}
