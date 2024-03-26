package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Order;

import java.util.List;

public interface OrderService {


    //     购票，增加订单
    Integer addTicketToPassenger(Order order);

    //    删除订单
    Integer deleteOrderById(Integer id);

//    更改订单的状态
    Integer modOrderState(Integer orderId, Integer state);



    //    乘客查看自己的所有订单
    List<Order> selectBySelfId(Integer id);




}
