package com.example.tpwsmartparking.service.impl;

import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.mapper.ParkingLotMapper;
import com.example.tpwsmartparking.service.ParkingLotService;
import com.example.tpwsmartparking.vo.ParkingLotVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//停车场管理
@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Override
    //将查询到的车场信息封装成Vo对象,得到ParkingLotVo对象
    public ParkingLotVo<ParkingLot> getParkingLotVo() {
        ParkingLotVo<ParkingLot> vo = new ParkingLotVo<>();
        vo.setCode(0);
        vo.setMsg("");
        //查询到的车场数量
        vo.setCount(parkingLotMapper.getParkingLotList().size());
        //查询到的车场信息
        vo.setData(parkingLotMapper.getParkingLotList());
        return vo;
    }
    //批量删除数据
    @Override
    public Integer deleteParkingLotList(String parkingIdList) {
        Integer integer = 0;
        String sub = null;
        sub = parkingIdList.substring(1, parkingIdList.length() - 1);
        if (sub.contains(",")) {
            for (String s : sub.split(",")) {
                int  parkingId = Integer.parseInt(s);
                parkingLotMapper.delParkingLot(parkingId);
            }
            return 1;
        } else {
            integer = Integer.parseInt(sub);
            return parkingLotMapper.delParkingLot(integer);
        }
    }

    @Override
    public String insertParkingLot(Integer parkingId, String parkingName, Integer parkingSpaceCount, Integer timingUnit, Integer unitCost) {
       if (parkingLotMapper.insertParkingLot(parkingId,parkingName,parkingSpaceCount,timingUnit,unitCost)==1){
           return "添加停车场成功！！！";
       }
       return "添加失败";
    }

    @Override
    public Integer updataParkingLot(Integer parkingId, String parkingName, Integer parkingSpaceCount, Integer timingUnit, Integer unitCost) {
        return parkingLotMapper.updataParkingLot(parkingId,parkingName,parkingSpaceCount,timingUnit,unitCost);
    }

    @Override
    public void delParkingLot(int parseInt) {
        parkingLotMapper.delParkingLot(parseInt);
    }

    @Override
    public ParkingLot getParkingLotByName(String parkingName) {
        return parkingLotMapper.getParkingLotByName(parkingName);
    }

}
