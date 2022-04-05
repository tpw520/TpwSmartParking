package com.example.tpwsmartparking.controller;

import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.service.ParkingLotService;
import com.example.tpwsmartparking.vo.ParkingLotVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//停车场管理
@Slf4j
@Controller
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;
    //parkingLot请求，跳转到parkingLot页面。
    @GetMapping("/parkingLot")
    public String parkingLot(){
        return "parkingLot";
    }

    //渲染数据，前端访问:/list接口，后端返回符合要求的json字符串-->parkingLotVo
    @RequestMapping("/list")
    @ResponseBody
    public ParkingLotVo<ParkingLot> parkingLotList() {
        return parkingLotService.getParkingLotVo();
    }

    //删除停车场 --> 批量删除
    @PostMapping("/deleteParkingLotList")
    @ResponseBody
    public Integer deleteParkingLotList(@RequestParam("parkingId") String parkingIdList){
        return parkingLotService.deleteParkingLotList(parkingIdList);
    }
    //跳转到停车场的添加页面
    @GetMapping("/addParkingLot")
    public String addParkingLot(){
        return "add";
    }

    //插入一条停车场记录
    @PostMapping("/insertParkingLot")
    @ResponseBody
    public String insertParkingLot(@RequestParam("parkingId") Integer parkingId,@RequestParam("parkingName") String parkingName, @RequestParam("parkingSpaceCount") Integer parkingSpaceCount
            , @RequestParam("timingUnit") Integer timingUnit,@RequestParam("unitCost") Integer unitCost){
        return parkingLotService.insertParkingLot(parkingId,parkingName,parkingSpaceCount,timingUnit,unitCost);
    }

    //获取前端编辑的数据
    @GetMapping("/editParkingLot")
    public String editParkingLot(@RequestParam("parkingId") Integer parkingId, @RequestParam("parkingName") String parkingName, @RequestParam("parkingSpaceCount") Integer parkingSpaceCount
            , @RequestParam("timingUnit") Integer timingUnit, @RequestParam("unitCost") Integer unitCost, HashMap<String,Object> map){
        map.put("parkingId",parkingId);
        map.put("parkingName",parkingName);
        map.put("parkingSpaceCount",parkingSpaceCount);
        map.put("timingUnit",timingUnit);
        map.put("unitCost",unitCost);
        log.info("**************");
        return "edit";
    }

    @ResponseBody
    @PostMapping("/updataParkingLot")
    public void updataParkingLot(@RequestParam("parkingId") Integer parkingId, @RequestParam("parkingName") String parkingName, @RequestParam("parkingSpaceCount") Integer parkingSpaceCount
            , @RequestParam("timingUnit") Integer timingUnit, @RequestParam("unitCost") Integer unitCost){
        log.info("##############################");
        log.info("{}",parkingId);
        parkingLotService.updataParkingLot(parkingId,parkingName,parkingSpaceCount,timingUnit,unitCost);
        log.info("&&&&&&&&&&&&&&&&&&&&&");
    }
    //根据id删除停车场
    @PostMapping("/deleteParkinglotId")
    @ResponseBody
    public String deleteParkinglotId(@RequestParam("parkingId") String id) {
        parkingLotService.delParkingLot(Integer.parseInt(id));
        return null;
    }
}
