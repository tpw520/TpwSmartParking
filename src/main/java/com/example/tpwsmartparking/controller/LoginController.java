package com.example.tpwsmartparking.controller;

import com.example.tpwsmartparking.entity.Admin;
import com.example.tpwsmartparking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//用户登录
@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;

    @GetMapping(value = {"/login", "/"})
    public String JudgeLoginUser() {
        return "login";
    }
    //登陆判断
    @PostMapping("/login")
    public String Login(@RequestParam("adminName") String adminName,
                        @RequestParam("adminPassword") String adminPassword,
                        HttpSession session,
                        Model model) {
        Admin admin = adminService.getAdmin(adminName);
        if (admin != null && admin.getAdminPassword().equals(adminPassword)) {
            //注入session 全局
            session.setAttribute("loginAdmin", admin);
            //重定向到主页面
            return "redirect:/index.html";
        }
        model.addAttribute("msg", "账号密码错误，请重新输入");
        return "login";
    }
    @GetMapping("/index.html")
    public String indexPage(HttpSession session,
                            Model model) {
        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        model.addAttribute("name", loginAdmin.getAdminName());
        return "index";
    }
}
