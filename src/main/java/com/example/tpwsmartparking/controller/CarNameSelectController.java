package com.example.tpwsmartparking.controller;

import com.example.tpwsmartparking.service.CarNameSelectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CarNameSelectController {
    @Resource
    private CarNameSelectService carNameSelectService;
    @GetMapping("/carNameSelect")
    public List<String> carNameSelect(){
        return carNameSelectService.selectNameList();
    }
}
