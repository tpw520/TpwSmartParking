package com.example.tpwsmartparking.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.mapper.ParkingLotPageMapper;
import com.example.tpwsmartparking.vo.ParkingLotVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotPageServiceIpmlTest {
    @Autowired
    private ParkingLotPageMapper parkingLotPageMapper;
    @Test
    void getParkingLotVo() {
        Page<ParkingLot> parkingLotPage = new Page<>(1, 10);
        IPage<ParkingLot> result = null;
        ParkingLotVo<ParkingLot> vo = new ParkingLotVo<>();
        if (parkingLotPageMapper != null) {
            result = parkingLotPageMapper.selectPage(parkingLotPage, null);
            vo.setCode(0);
            vo.setMsg("");
            //查询到的车场数量
//        IPage<ParkingLot> parkingLotPage = new Page<>(page, limit);
//        IPage<ParkingLot> result = parkingLotMapper.selectPage(parkingLotPage, null);
            vo.setCount((int) result.getTotal());
            //查询到的车场信息
            vo.setData(result.getRecords());
        }
//        ParkingLotVo<ParkingLot> vo = new ParkingLotVo<>();
//        vo.setCode(0);
//        vo.setMsg("");
//        //查询到的车场数量
////        IPage<ParkingLot> parkingLotPage = new Page<>(page, limit);
////        IPage<ParkingLot> result = parkingLotMapper.selectPage(parkingLotPage, null);
//        vo.setCount((int) result.getTotal());
//        //查询到的车场信息
//        vo.setData(result.getRecords());
        System.out.println(vo);
    }

}