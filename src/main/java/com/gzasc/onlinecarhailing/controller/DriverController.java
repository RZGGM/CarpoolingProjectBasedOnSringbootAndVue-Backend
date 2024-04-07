package com.gzasc.onlinecarhailing.controller;


import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.CarService;
import com.gzasc.onlinecarhailing.service.DriverSerivce;
import com.gzasc.onlinecarhailing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Objects;

@RestController
public class DriverController {

    @Autowired
    CarService carService;
    @Autowired
    OrderService orderService;
    @Autowired
    DriverSerivce driverSerivce;

    //  通过所有者的id，查询车
    @RequestMapping("/driver/myCar")
    public Result seekCars(Integer ownerId) {

        List<Car> cars = carService.searchByOwnerId(ownerId);


        if (!cars.isEmpty()) return Result.success("查询成功", cars);
        else return Result.error("没有车");

    }

    //    查看自己的订单
    @RequestMapping("/driver/orders")
    public Result seekOrders(Integer driverId) {

        List<Order> orders = orderService.selectBySelfId(driverId);

        if (orders.isEmpty()) return Result.success("没有订单");
        else return Result.success("查询成功", orders);

    }

    //查看可以接单的订单
    @RequestMapping("/driver/viewCanAcceptOrder")
    public Result viewCanAcceptOrder() {

        List<Order> orders = orderService.searchOrdersByOrderState(OrderState.PASSENGER_CREATE_SHARE_BILL_WAIT_DRIVER);

        if (orders == null) return Result.error("查询乘客发起的拼单订单失败");

        if (!orders.isEmpty()) return Result.success("查看乘客发起的拼单订单成功", orders);

        else return Result.success("没有可以接受乘客发起的拼单订单");

    }

    //    接单
    @RequestMapping("/driver/acceptOrder")
    public Result acceptOrder(String passengerOrderId, Integer driverId) {

        if (passengerOrderId == null || driverId == null) return Result.error("错误，订单或是司机的id为null");

//        根据乘客订单的id，来接受订单

        Integer count = driverSerivce.addOrderToDriver(passengerOrderId, driverId);


        if (Objects.equals(count, OrderState.HAS_DRIVER)) return Result.error("失败，已经有司机接单了。");
        else return Result.success("成功", count);

    }
//    发出拼单
    @RequestMapping("/driver/createShareTheBill")
    public Result createShareTheBill(@RequestBody Order order) {

        if (null == order) return Result.error("订单为null");

        if (null == order.getOrderType()) return Result.error("订单类型为null");

//        判断订单是不是拼单类型的订单，如果不是就return。
        if (JoinOrderCanJoin.NON_JOIN_ORDER.equals(
                order.getJoinOrderCanJoin()
        )) return Result.error("并非是拼单订单。");

//        判断订单类型。
        if (OrderType.PASSENGER_CREATE_ORDER.equals(order.getOrderType())) {
//如果是乘客发起的订单就进来
            Integer count = orderService.addJoinOrderToPassenger(order);

            if (count > 0) return Result.success("创建订单成功");

            else return Result.error("创建订单失败");
        } else if (OrderType.DRIVER_CREATE.equals(order.getOrderType())) {
//            如果是司机发起的就进入到这

            Integer count = orderService.addJoinOrderToDriver(order);

            if (count > 0) return Result.success("创建订单成功");

        }else return Result.error("订单类型错误");
        return Result.error("创建订单失败，因为类型不符合。");
    }

    @RequestMapping("/driver/cancelOrder")
    public Result cancelOrder(@RequestBody String orderId) {


        Integer count = orderService.abolishOrderByOrderId(orderId);

        if (count > 0) return Result.success("司机取消订单成功");
        else return Result.error("司机取消订单失败");

    }

}
