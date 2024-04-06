package com.gzasc.onlinecarhailing.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.OrderService;
import com.gzasc.onlinecarhailing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
    public Result buyTicket(@RequestBody Order order1) {


        String format = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        String numbers = RandomUtil.randomNumbers(5);
        String orderCode = format + numbers;

        Order order = (Order) order1;

//         设置订单的编号
        order.setOrderId(orderCode);

        if (order.getPassengerId() == null || order.getTicketId() == null)
            return Result.error("购票失败了，传入的信息为空。");


        Integer count = orderService.addTicketToPassenger(order);


        if (count > 0) {

            return Result.success("买票成功");


        } else return Result.error("买票失败，没有票了。");


    }

    //    支付订单
    @RequestMapping("/passenger/payOrder")
    public Result payOrder(@RequestBody String orderId) {

        Integer count = orderService.modOrderState(orderId, OrderState.WAIT_CONFIRM);

        if (count > 0) {

            return Result.success("订单支付成功");


        } else return Result.error("买票失败，没有票了。");

    }


    // 确认订单
    @RequestMapping("/passenger/confirmOrder")
    public Result confirmOrder(@RequestBody String orderId) {

        Integer count = orderService.modOrderState(orderId, OrderState.FINSHED);

        if (count > 0) {

            return Result.success("订单支付成功");


        } else return Result.error("买票失败，没有票了。");

    }

    //    发出一个拼单订单
    @RequestMapping("/passenger/createShareTheBill")
    public Result createShareTheBill(@RequestBody  Order order) {

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
    @RequestMapping("/passenger/cancelOrder")
    public Result cancelOrder(@RequestBody String orderId) {


        Integer count = orderService.abolishOrderByOrderId(orderId);

        if (count > 0) return Result.success("乘客取消订单成功");
        else return Result.error("乘客取消订单失败");

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


    // 对订单进行评价
    @RequestMapping("/passenger/commentOrder")
    public Result commentOrder(@RequestBody  Appraise appraise) {

        Integer count = orderService.addAppraiseIdToOrder(appraise);

        return Result.success("成功", count);

    }

    @RequestMapping("/passenger/viewCanJoinOrder")
    public Result viewCanJoinOrder() {
//  查看所有可以拼单的订单

        List<Order> orders = orderService.searchOrdersByOrderType(OrderType.PASSENGER_CAN_JOIN);

        if (!orders.isEmpty()) return Result.success("成功", orders);
        else return Result.success("没有可以拼单的订单");

    }
    @RequestMapping("/passenger/viewAppraise")
    public Result viewAppraise(@RequestBody String orderId){

//        判断传入的参数是否正确
        if (orderId == null) return Result.error("查询评价传入的订单的编号为null");

        Appraise appraise = orderService.searchAppraiseByOrderId(orderId);

        if (appraise != null) return Result.success("查询订单评价成功", appraise);

        else return Result.success("查询订单评价失败", null);

    }

}
