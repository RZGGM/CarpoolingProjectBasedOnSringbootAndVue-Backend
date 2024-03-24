package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;

import java.util.List;

public interface DriverSerivce {

    //    注册帐号
    public Integer register(Driver driver);
    //    删除，通过id
    public Integer remove(Integer id);
    //    修改
    public Integer mod(Driver newDriver);
    //    根据id查询
    public Driver search(Integer id);
    //    查询所有
    public List<Driver> searchAll();
}
