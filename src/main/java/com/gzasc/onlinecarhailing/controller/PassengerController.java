package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.OrderService;
import com.gzasc.onlinecarhailing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//乘客的接口

@RestController
public class PassengerController {

    @Autowired
    PassengerService passengerService;
    @Autowired
    OrderService orderService;



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
//    购票
    public Result buyTicket(Order order){

        Integer count = orderService.addTicketToPassenger(order);

        if (count > 0){

            return Result.success("买票成功");


        }else return Result.error("买票失败");


    }
//    查看自己的所有订单
    public Result viewAllOrder(Integer id){

        List<Order> orders = orderService.selectBySelfId(id);

        return Result.success("查看成功",orders );


    }
//    查看订单详细,应该是前端的


//    删除订单
    public Result removeOrderByOrderId(Integer id){

        Integer count = orderService.deleteOrderById(id);

        if (count > 0) return Result.success("删除订单成功");
        else return Result.error("删除失败");

    }

//    确认订单
    public Result confirmOrder(Order order){



        return Result.success("成功");
    }

}
