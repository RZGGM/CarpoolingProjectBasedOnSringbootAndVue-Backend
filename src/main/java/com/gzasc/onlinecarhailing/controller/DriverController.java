package com.gzasc.onlinecarhailing.controller;


import com.gzasc.onlinecarhailing.pojo.Car;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    CarService carService;

    //  通过所有者的id，查询车
    @RequestMapping("/driver/myCar")
    public Result seekCars(Integer ownerId){

        List<Car> cars = carService.searchByOwnerId(ownerId);


        if (!cars.isEmpty()) return Result.success("查询成功", cars);
        else return Result.error("没有车");

    }

}
