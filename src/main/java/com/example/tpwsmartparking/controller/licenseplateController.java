package com.example.tpwsmartparking.controller;

import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.service.ParkingLotService;
import com.example.tpwsmartparking.service.ParkingRecordService;
import com.example.tpwsmartparking.utils.baiduiApi.WebImage;
import com.example.tpwsmartparking.utils.customize.FileJudgeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;

//车牌识别
@Slf4j
@Controller
public class licenseplateController {
    @Autowired
    private ParkingRecordService parkingRecordService;
    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping("/licenseplate")
    public String licenseplate() {
        return "licenseplate";
    }

    @PostMapping("/imageRecognition")
    public String licenseplate(@RequestPart("fileName") MultipartFile fileName, @RequestParam("parkingName") String parkingName) {
//        String numberPlate = FileJudgeUtil.fileJudgeUtil(fileName);
        String originalFilename = null;
        String numberPlate = null;
        //判断传入的文件是否为空，防止后面出现空指针异常
        if (!fileName.isEmpty()) {
            originalFilename = fileName.getOriginalFilename();
            //识别车牌号
            numberPlate = WebImage.webImage("D://resource/pictures/" + originalFilename);
        }
        log.info("**************{}", numberPlate);
        String status = parkingRecordService.selectParkingRecordExist(numberPlate);
        log.info("{}", "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%-=-------------=" + status);
        if (status == null) {
            parkingRecordService.insertParkingRecord(parkingName, numberPlate, LocalDateTime.now(), null, 0, "1");
        } else {
            log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%-=-------------=");
            ParkingLot parkingLot = parkingLotService.getParkingLotByName(parkingName);
            Integer unitCost = null;
            Integer timingUnit = null;
            if (parkingLot != null) {
                unitCost = parkingLot.getUnitCost();
                timingUnit = parkingLot.getTimingUnit();
            }
            ParkingRecord parkingRecord = parkingRecordService.getParkingRecord(numberPlate);
            LocalDateTime inDateTime = parkingRecord.getInDateTime();
            long minutes = Duration.between(inDateTime, LocalDateTime.now()).toMinutes();
            int money = (int) (minutes / timingUnit * unitCost);

            parkingRecordService.updataParkingRecord(parkingName, numberPlate, inDateTime, LocalDateTime.now(), money, "0");
        }
        return "licenseplate";
    }
}
