package com.example.tpwsmartparking.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.mapper.ParkingRecordMapper;
import com.example.tpwsmartparking.service.ParkingRecordService;
import com.example.tpwsmartparking.vo.ParkingRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ParkingRecordServiceImpl implements ParkingRecordService {
    @Autowired
    private ParkingRecordMapper parkingRecordMapper;

    //检查目前的停车状态
    @Override
    public String selectParkingRecordExist(String imageNumber) {
        return parkingRecordMapper.selectParkingRecordExist(imageNumber);
    }

    //插入一条停车记录
    @Override
    public void insertParkingRecord(String parkingName, String imageNumber, LocalDateTime now, LocalDateTime end, int money, String status) {
        parkingRecordMapper.insertParkingRecord(parkingName, imageNumber, now, end, money, status);
    }

    //修改停车状态
    @Override
    public void updataParkingRecord(String parkingName, String numberPlate, LocalDateTime now, LocalDateTime end, int money, String status) {
        parkingRecordMapper.updataParkingRecord(parkingName, numberPlate, now, end, money, status);
    }

    //查询一条停车记录（根据车牌号）
    @Override
    public ParkingRecord getParkingRecord(String numberPlate) {
        return parkingRecordMapper.getParkingRecord(numberPlate);
    }

    //查询所有的停车记录
    @Override
    public ParkingRecordVo<ParkingRecord> getParkRecordAll(Integer page,Integer limit) {
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, null);
        vo.setCount((int) result.getTotal());
        List<ParkingRecord> parkingRecordAll = result.getRecords();
        //对数据再进行一次渲染，返回符合要求的数据
        for (ParkingRecord parkingRecord : parkingRecordAll) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        vo.setData(parkingRecordAll);
        return vo;
    }
}
