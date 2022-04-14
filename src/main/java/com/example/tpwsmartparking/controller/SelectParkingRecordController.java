package com.example.tpwsmartparking.controller;

import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.service.ParkingRecordService;
import com.example.tpwsmartparking.service.SelectParkingRecordService;
import com.example.tpwsmartparking.vo.ParkingRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Controller
@Slf4j
public class SelectParkingRecordController {
    @Autowired
    private SelectParkingRecordService selectParkingRecordService;
    @GetMapping("/parkingRecordNumberPlate")
    @ResponseBody
    public ParkingRecordVo<ParkingRecord> parkingRecordNumberPlate(@RequestParam("NumberPlate") String NumberPlate,Integer page, Integer limit){
        return selectParkingRecordService.ParkRecordNumberPlate(NumberPlate,page,limit);
    }
    @GetMapping("/parkingRecordDate")
    @ResponseBody
    public ParkingRecordVo<ParkingRecord> parkingRecordDate(@RequestParam("date1") String startTime,@RequestParam("date2") String endTime,Integer page,Integer limit){

        return selectParkingRecordService.RecordExist(startTime,endTime,page,limit);
    }

}
