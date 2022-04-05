package com.example.tpwsmartparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//推出登录
@Controller
public class logoutController {
    @GetMapping("/logout")
    public String Layout(HttpServletRequest request){
        HttpSession session = request.getSession();
        //移除session
        session.removeAttribute("loginAdmin");
        return "login";
    }
}
