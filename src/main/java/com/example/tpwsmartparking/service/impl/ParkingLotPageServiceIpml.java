package com.example.tpwsmartparking.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tpwsmartparking.entity.ParkingLot;
import com.example.tpwsmartparking.mapper.ParkingLotPageMapper;
import com.example.tpwsmartparking.service.ParkingLotPageService;
import com.example.tpwsmartparking.vo.ParkingLotVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotPageServiceIpml implements ParkingLotPageService {
    @Autowired
    private ParkingLotPageMapper parkingLotPageMapper;
    @Override
    public ParkingLotVo<ParkingLot> getParkingLotVo(Integer page, Integer limit) {
        IPage<ParkingLot> parkingLotPage = new Page<>(page, limit);
        IPage<ParkingLot> result = parkingLotPageMapper.selectPage(parkingLotPage, null);
        ParkingLotVo<ParkingLot> vo = new ParkingLotVo<>();
        vo.setCode(0);
        vo.setMsg("");
        //查询到的车场数量
//        IPage<ParkingLot> parkingLotPage = new Page<>(page, limit);
//        IPage<ParkingLot> result = parkingLotMapper.selectPage(parkingLotPage, null);
        vo.setCount((int) result.getTotal());
        //查询到的车场信息
        vo.setData(result.getRecords());
        return vo;
    }
}
