package com.example.tpwsmartparking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tpwsmartparking.entity.ParkingRecord;
import com.example.tpwsmartparking.mapper.SelectParkingRecordMapper;
import com.example.tpwsmartparking.service.SelectParkingRecordService;
import com.example.tpwsmartparking.vo.ParkingRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectParkingRecordServiceImpl implements SelectParkingRecordService {
    @Autowired
    private SelectParkingRecordMapper selectParkingRecordMapper;
    @Override
    public ParkingRecordVo<ParkingRecord> RecordExist(String startTime, String endTime,Integer page,Integer limit) {
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
//        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
//        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.ge("in_date_time", startTime);
//        objectQueryWrapper.le("in_date_time", endTime);
//        IPage<ParkingRecord> result = selectParkingRecordMapper.selectPage(parkingRecordPage, parkingRecordPage);
        List<ParkingRecord> parkingRecordAll = selectParkingRecordMapper.RecordExist(startTime, endTime);
        vo.setCount(parkingRecordAll.size());
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

    @Override
    public ParkingRecordVo<ParkingRecord> ParkRecordNumberPlate(String numberPlate, Integer page, Integer limit) {
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        List<ParkingRecord> parkingRecordAll = selectParkingRecordMapper.ParkRecordNumberPlate(numberPlate);
        vo.setCount(parkingRecordAll.size());
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
