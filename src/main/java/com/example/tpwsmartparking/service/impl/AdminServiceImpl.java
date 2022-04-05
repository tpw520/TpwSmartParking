package com.example.tpwsmartparking.service.impl;

import com.example.tpwsmartparking.entity.Admin;
import com.example.tpwsmartparking.mapper.LoginAdminMapper;
import com.example.tpwsmartparking.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private LoginAdminMapper loginAdmin;
    @Override
    public Admin getAdmin(String adminName) {
        return loginAdmin.getAdmin(adminName);
    }
}
