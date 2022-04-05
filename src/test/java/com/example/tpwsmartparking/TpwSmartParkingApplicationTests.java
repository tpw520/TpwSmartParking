package com.example.tpwsmartparking;

import com.example.tpwsmartparking.service.ParkingLotService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
class TpwSmartParkingApplicationTests {
    @Autowired
    private ParkingLotService parkingLotService;
    @Test
    void contextLoads() {
        log.info("{}", parkingLotService.getParkingLotVo());

    }

}
