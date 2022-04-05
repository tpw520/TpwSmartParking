package com.example.tpwsmartparking.controller;

import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.service.ParkingRecordService;
import com.example.tpwsmartparking.vo.ParkingRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//停车记录
@Slf4j
@Controller
public class parkingRecordController {
    @Autowired
    private ParkingRecordService parkingRecordService;
    @GetMapping("/parkingRecordAll")
    @ResponseBody
    public ParkingRecordVo<ParkingRecord> parkingRecordAll(){
        return parkingRecordService.getParkRecordAll();
    }
    @GetMapping("/parkingRecord")
    public String parkingRecord(){
        return "parkingRecord";
    }

}
