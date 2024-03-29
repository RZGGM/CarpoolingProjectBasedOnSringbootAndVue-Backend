package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.*;
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
    @RequestMapping("/passenger/modPersonalInfo")
    public Result alterUserData(Passenger passenger) {

        if (passengerService.mod(passenger) > 0) {
//            修改成功
            return Result.success("修改成功");

        } else return Result.error("修改失败");

    }

    //    查看个人信息
    @RequestMapping("/passenger/checkPersonalInfomation")
    public Result searchById(Integer id) {

        Passenger passenger = passengerService.search(id);

        if (null != passenger) {

            return Result.success("查看成功", passenger);

        } else return Result.error("查看失败");

    }

    //    购票
    @RequestMapping("/passenger/buyTicket")
    public Result buyTicket(Order order) {

        Integer count = orderService.addTicketToPassenger(order);

        if (count > 0) {

            return Result.success("买票成功");


        } else return Result.error("买票失败");


    }

    //    发出一个拼单订单
    @RequestMapping("/passenger/createSHareTheBillP")
    public Result createSHareTheBillP(Order order) {

        if (null == order) return Result.error("订单为null");

        if (null == order.getOrderType()) return Result.error("订单类型为null");

//        判断订单类型。如果是一乘客发起的订单就进行
        if (OrderType.PASSENGER_CREATE_ORDER.equals(order.getOrderType())) {

//            将订单类型改为让司机可以看到
            order.setState(OrderType.PASSENGER_WAIT_DRIVER);

            Integer count = orderService.addTicketToPassenger(order);

            if (count > 0) return Result.success("创建订单成功");

            else return Result.error("创建订单失败");
        }
        return Result.error("创建订单失败");
    }

//    取消自己的订单
    public Result cancelOrder(String orderId){


        Integer count = orderService.abolishOrderByOrderId(orderId);

        if (count > 0) return Result.success("取消成功");
        else return Result.error("取消失败");

    }

    //    查看自己的所有订单
    @RequestMapping("/passenger/allOrder")
    public Result viewAllOrder(Integer id) {

        List<Order> orders = orderService.selectBySelfId(id);

        return Result.success("查看成功", orders);


    }
//    查看订单详细,应该是前端的


    //    删除订单
    @RequestMapping("/passenger/deleteOrder")
    public Result removeOrderByOrderId(Integer id) {

        Integer count = orderService.deleteOrderById(id);

        if (count > 0) return Result.success("删除订单成功");
        else return Result.error("删除失败");

    }

//    确认订单

    public Result confirmOrder(Order order) {


        return Result.success("成功");
    }

    // 对订单进行评价
    @RequestMapping("/passenger/commentOrder")
    public Result commentOrder(Appraise appraise) {

        Integer count = orderService.addAppraiseIdToOrder(appraise);

        return Result.success("成功", count);

    }
    @RequestMapping("/passenger/viewCanJoinOrder")
    public Result viewCanJoinOrder(){
//  查看所有可以拼单的订单

        List<Order> orders = orderService.searchOrdersByOrderType(OrderType.PASSENGER_CAN_JOIN);

        if (!orders.isEmpty()) return Result.success("成功", orders);
        else return Result.success("没有可以拼单的订单");

    }

}
