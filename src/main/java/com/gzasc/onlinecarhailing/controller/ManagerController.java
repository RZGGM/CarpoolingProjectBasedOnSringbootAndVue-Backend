package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//管理员用的接口
// 使用@Controller注解在前端发送Axios时会报错。不知道为什么，找到了，是因为少了@@ResponseBody这个注解

@Slf4j
@RestController
public class ManagerController {
    @Autowired
    AccountService accountService;
    @Autowired
    TicketService ticketService;
    @Autowired
    CarService carService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    DriverSerivce driverSerivce;

    //    删除乘客的
    @RequestMapping("/system/removePassengersByIds")
    public Result removePassengersByIds(@RequestBody HashMap<String, Integer[]> ids) {

//    前端传的数组类型的数据，不知道为什么会变成HashMap类型的，所以只能处理成List类型的了。
        Integer[] ids1 = ids.get("ids");
        if (ids1 == null) return Result.error("要删除的id为空");
        if (ids1.length == 0) return Result.error("要删除的id的数组长度为0");
        ArrayList<Integer> ids2 = new ArrayList<>(ids1.length);

        Collections.addAll(ids2, ids1);

        Integer countAccount = passengerService.removeByIds(ids2);

        if (countAccount > 0) return Result.success("批量删除成功");
        else return Result.error("批量删除失败");

    }


    //    管理员后台修改帐号信息，如改密码什么的。
    @RequestMapping("/system/editAccount")
    public Result editAccount(Account account) {

        Integer count = accountService.mod(account);

        if (0 != count) return Result.success("修改成功", count);

        return Result.error("修改失败");

    }

    //    管理员后台，删除帐号
//    @RequestMapping("/system/deleteAccountsById")

    @RequestMapping("/system/removeAccountsById")
    public Result removeAccountsById(@RequestBody HashMap<String, Integer[]> ids) {

//        log.info(idsArr.toString());


        Integer[] ids1 = ids.get("ids");

        if (ids1 == null) return Result.error("要删除的id为空");
        if (ids1.length == 0) return Result.error("要删除的id的数组长度为0");
        ArrayList<Integer> ids2 = new ArrayList<Integer>(ids1.length);
        Collections.addAll(ids2, ids1);

        Integer countAccount = ticketService.removeTickets(ids2);

        if (countAccount > 0) return Result.success("批量删除成功");
        else return Result.error("批量删除失败");

    }


    //增加一张车票
    @RequestMapping("/system/addTicket")
    public Result addTicket(@RequestBody Ticket ticket) {

        if (null == ticket) return Result.error("要添加的票是null，添加失败");

        log.info(String.valueOf(ticket));

        Ticket ticket1 = ticketService.searchTicketById(ticketService.addTicket(ticket));

//        System.out.println();

        if (null != ticket1) {
            return Result.success("添加成功", ticket1);
        } else return Result.error("添加失败");

    }

    //    删除一张车票
    public Result removeTicketById(Integer id) {

        Integer count = ticketService.removeTicket(id);

        if (count > 0) {
            return Result.success("删除成功", count);
        } else return Result.error("删除失败");

    }

    //    批量删除车票
    @RequestMapping("system/deleteTicketsByIds")
    public Result removeTicketsByIds(List<Integer> ids) {

        Integer count = ticketService.removeTickets(ids);

        if (count > 0) {
            return Result.success("批量删除成功", ids);
        } else return Result.error("失败");

    }

    //    修改车票信息
    public Result editTicket(Ticket ticket) {

        Integer count = ticketService.modTicket(ticket);

        ticket = ticketService.searchTicketById(ticket.getId());

        if (count > 0) {
            return Result.success("批量删除成功", ticket);
        } else return Result.error("失败");
    }

    //增加车
    @RequestMapping("")
    public Result addCar(Car car) {
        Integer count = carService.addCar(car);

        if (count > 0) {
            return Result.success("增加成功", car);
        } else return Result.error("失败");


    }

    //    删除一车
    public Result removeCar(Integer carId) {
        Integer count = carService.remove(carId);

        if (count > 0) {
            return Result.success("删除成功", carId);
        } else return Result.error("失败");

    }

    //    批量删除
    public Result removeCars(List<Integer> ids) {
        Integer count = carService.removeByIds(ids);

        if (count > 0) {
            return Result.success("删除成功", ids);
        } else return Result.error("失败");
    }

    //    修改
    public Result editCar(Car car) {
        Integer count = carService.mod(car);

        if (count > 0) {
            return Result.success("修改成功", car);
        } else return Result.error("失败");
    }

    //    通过id查一辆车
    public Result seekCarById(Integer id) {

        Car car = carService.searchById(id);

        if (null != null) {
            return Result.success("删除成功", car);
        } else return Result.error("失败");

    }

    //查询所有的车
    public Result seekAllCar() {

        List<Car> cars = carService.searchAll();

        return Result.success("查询成功", cars);

    }

    //    查询所有的乘客
    @RequestMapping("/system/seekAllPassengers")
    public Result seekAllPassengers() {

        List<Passenger> passengers = passengerService.searchAll();

        return Result.success("查询成功", passengers);

    }

    //    查看所有司机
    @RequestMapping("/system/seekAllDrivers")
    public Result seekAllDrivers() {

        List<Driver> drivers = driverSerivce.searchAll();

        return Result.success("查看所有司机成功", drivers);

    }

//    查看所有的帐号
@RequestMapping("/system/seekAllAccounts")
    public Result seekAllAccounts(){


        List<Account> accountList = accountService.searchAll();

        return Result.success("查看所有帐号成功", accountList);

    }

}
