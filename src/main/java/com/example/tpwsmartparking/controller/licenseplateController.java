package com.example.tpwsmartparking.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.service.ParkingLotService;
import com.example.tpwsmartparking.service.ParkingRecordService;
import com.example.tpwsmartparking.utils.baiduiApi.WebImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
        log.info("{}-------------------------->", fileName.getName());
        String originalFilename = null;
        String numberPlate = null;
        //判断传入的文件是否为空，防止后面出现空指针异常
        if (!fileName.isEmpty()) {
            originalFilename = fileName.getOriginalFilename();
            log.info("{}", originalFilename);
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

    @PostMapping("/imageRecognition1")
    public String licenseplate1(@RequestParam("fileName") MultipartFile multipartFile, @RequestParam("parkingName") String parkingName) {

        String originalFilename = null;
        String numberPlate = null;
        String name = "Images" + RandomUtil.randomInt(1, 1000) + ".jpg";
        //判断传入的文件是否为空，防止后面出现空指针异常
        if (!multipartFile.isEmpty()) {
            copyFileWithBuffered(multipartFile, name);
            originalFilename = multipartFile.getOriginalFilename();
            log.info("{}", originalFilename);
            //识别车牌号
            numberPlate = WebImage.webImage("D://resource/pictures/" + name);
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
            int money = 0;
            if (unitCost != null && timingUnit != null) {
                money = (int) (minutes / timingUnit * unitCost);
            }

            parkingRecordService.updataParkingRecord(parkingName, numberPlate, inDateTime, LocalDateTime.now(), money, "0");
        }
        return "licenseplate";
    }

    //将MultipartFile转换成文件
    public void copyFileWithBuffered(MultipartFile multipartFile, String name) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
//            String fileName = multipartFile.getOriginalFilename();
            File destFile = new File("D://resource/pictures/" + name);
            //2.造流
            //2.1 造节点流
//            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造缓冲流
            InputStream inputStream = multipartFile.getInputStream();
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
