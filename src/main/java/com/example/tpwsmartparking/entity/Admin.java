package com.example.tpwsmartparking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
public class Admin {
    //管理员name
    private String adminName;
    //管理员密码
    private String adminPassword;
}
