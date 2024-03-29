package com.gzasc.onlinecarhailing.controller;


import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.CarService;
import com.gzasc.onlinecarhailing.service.DriverSerivce;
import com.gzasc.onlinecarhailing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<Order> orders = orderService.searchOrdersByOrderType(OrderType.PASSENGER_WAIT_DRIVER);

        if (!orders.isEmpty()) return Result.success("查看成功", orders);
        else return Result.success("没有可以接受的订单");

    }

    //    接单
    @RequestMapping("/driver/acceptOrder")
    public Result acceptOrder(String passengerOrderId, Integer driverId) {

//        根据乘客订单的id，来接受订单

        Integer count = driverSerivce.addOrderToDriver(passengerOrderId, driverId);

        if (Objects.equals(count, OrderState.HAS_DRIVER)) return Result.error("失败，已经有司机接单了。");
        else return Result.success("成功", count);

    }
}
