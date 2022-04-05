package com.example.tpwsmartparking.service.impl;

import com.example.tpwsmartparking.mapper.CarNameSelectMapper;
import com.example.tpwsmartparking.service.CarNameSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarNameSelectServiceImpl implements CarNameSelectService {
    @Autowired
    private CarNameSelectMapper carNameSelectMapper;
    @Override
    public List<String> selectNameList() {
        return carNameSelectMapper.selectNameList();
    }
}
