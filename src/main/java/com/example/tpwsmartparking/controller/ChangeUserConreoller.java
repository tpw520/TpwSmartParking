package com.example.tpwsmartparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ChangeUserConreoller {

    @GetMapping("/ChangeUser")
    public String licenseplate() {
        return "ChangeUser";
    }

}
