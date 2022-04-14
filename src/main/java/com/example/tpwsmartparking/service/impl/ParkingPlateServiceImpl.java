package com.example.tpwsmartparking.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.tpwsmartparking.entity.ParkingPlate;
import com.example.tpwsmartparking.mapper.ParkingPlateMapper;
import com.example.tpwsmartparking.service.ParkingPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingPlateServiceImpl implements ParkingPlateService {
    @Autowired
    private ParkingPlateMapper parkingPlateMapper;

    @Override
    public List<ParkingPlate> getParkingPlateList() {

        return parkingPlateMapper.getParkingPlateList();
    }
}
