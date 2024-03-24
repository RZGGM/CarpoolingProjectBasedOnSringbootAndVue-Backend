package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//乘客的接口

@RestController
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    // 修改个人信息
    @RequestMapping("/modPersonalInfo")
    public Result alterUserData(Passenger passenger){

        if ( passengerService.mod(passenger) > 0){
//            修改成功
            return Result.success("修改成功");

        }else return Result.error("修改失败");

    }
    //    查看个人信息
    @RequestMapping("/checkPersonalInfomation")
    public Result searchById(Integer id){

        Passenger passenger = passengerService.search(id);

        if (null != passenger){

            return Result.success("查看成功", passenger);

        }else return Result.error("查看失败");

    }

}
