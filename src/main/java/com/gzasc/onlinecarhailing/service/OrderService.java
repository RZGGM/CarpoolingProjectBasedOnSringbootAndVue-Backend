package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Appraise;
import com.gzasc.onlinecarhailing.pojo.Order;

import java.util.List;

public interface OrderService {


    //     购票，增加订单
    Integer addTicketToPassenger(Order order);

    //    删除订单
    Integer deleteOrderById(Integer id);

//    更改订单的状态
    Integer modOrderState(String orderId, Integer state);

    //    乘客查看自己的所有订单
    List<Order> selectBySelfId(Integer id);

//    乘客对订单进行评价
    Integer addAppraiseIdToOrder(Appraise appraise);

//    通过订单的编号进行取消订单
    Integer abolishOrderByOrderId(String orderId);

//    根据订单的状态查询订单
    List<Order> searchOrdersByOrderState(Integer state);
//    根据订单的类型来查询订单
    List<Order> searchOrdersByOrderType(Integer type);


}
