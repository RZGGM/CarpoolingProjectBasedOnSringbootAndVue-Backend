package com.gzasc.onlinecarhailing.controller;


import com.gzasc.onlinecarhailing.pojo.Car;
import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.CarService;
import com.gzasc.onlinecarhailing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
public class DriverController {

    @Autowired
    CarService carService;
    @Autowired
    OrderService orderService;

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

    //    接单
    @RequestMapping("/driver/acceptOrder")
    public Result acceptOrder(Integer passengerOrderId, Integer driverId){

//        根据乘客订单的id，来接受订单

        orderService.add


    }
}
