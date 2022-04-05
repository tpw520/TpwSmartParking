package com.example.tpwsmartparking.mapper;

import com.example.tpwsmartparking.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginAdminMapper {
    @Select("select * from admin where admin_name = #{adminName} ")
    Admin getAdmin(String adminName);
}
