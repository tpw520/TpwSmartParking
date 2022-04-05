package com.example.tpwsmartparking.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingRecord {
    private String parkingName;
    private String numberPlate;
    private LocalDateTime inDateTime;
    private LocalDateTime outDateTime;
    private Integer money;
    private String status;
}
