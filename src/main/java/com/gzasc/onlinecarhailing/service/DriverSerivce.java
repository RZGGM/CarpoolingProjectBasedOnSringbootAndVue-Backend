package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Appraise;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;

import java.util.List;

public interface DriverSerivce {

    //    注册帐号
     Integer register(Driver driver);


    //    删除，通过id
     Integer remove(Integer id);
    //    修改
     Integer mod(Driver newDriver);
    //    根据id查询
     Driver search(Integer id);
    //    查询所有
     List<Driver> searchAll();

//     更新司机对应的评价
    Integer modAppraise(Integer appraiseId);
//    接受订单
    Integer addOrderToDriver(String passengerOrderId, Integer driverId);
}
