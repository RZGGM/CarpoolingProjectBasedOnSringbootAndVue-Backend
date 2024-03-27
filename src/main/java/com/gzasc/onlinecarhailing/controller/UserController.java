package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//用户都有的接口
@RestController
@Slf4j
public class UserController {

    @Autowired
    AccountService accountService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    DriverSerivce driverSerivce;
    @Autowired
    TicketService ticketService;

    //    登录
    @RequestMapping("/login")
    public Result login(Account account) {

        if (account == null) return Result.error("错误");

        String password = account.getPassword();

        log.info(account.toString());


        Integer type = account.getIt();

        Account account1 = accountService.search(account.getAccount());


        if (type == null || type > Type.OFFICIAL) return Result.error("错误");

        else {
            if (type.equals(Type.PASSENGER) && null != account1.getPassengerId()) {
                return Result.success("成功", account1);
            } else if (type.equals(Type.DRIVER) && null != account1.getDriverId()) {
                return Result.success("成功", account1);
            } else return Result.error("登录失败");
        }

    }


    //    注册成为用户
    @RequestMapping("/register")
    public Result registerPassenger(Account account) {
//        处理下null
        if (null == account.getAccount() || null == account.getIt()) return Result.error("要输入东酉");
//        要先判断帐号是否存在
        if (accountService.search(account.getAccount()) != null) {

//            帐号已经存在了，应该要修改帐号再注册
            return Result.error("帐号已经存在。");

        } else {

            if (account.getIt() == 0) {
//            先注册了帐号
                accountService.register(account);
                Integer accountId = account.getId();
                Passenger passenger = new Passenger();
                passenger.setAccount(account.getAccount());
                passenger.setAccountId(accountId);
                Integer pasengerId = passengerService.register(passenger);

                account.setPassengerId(pasengerId);

                accountService.mod(account);

                return Result.success("乘客注册成功", passengerService.search(pasengerId));

            } else if (account.getIt() == 1) {
                //            先注册了帐号
                accountService.register(account);
                Integer accountId = account.getId();
                Driver driver = new Driver();
                driver.setAccount(account.getAccount());
                driver.setAccountId(accountId);
                driverSerivce.register(driver);
                Integer driverId = driver.getDriverId();

                account.setDriverId(driverId);

                accountService.mod(account);

                return Result.success("司机注册成功", driverSerivce.search(driverId));
            } else return Result.error("注册失败");
        }

    }


    //    修改帐号密码
    @RequestMapping("/user/newPassword")
    public Result alterAccountPassword(Account account) {

        Integer count = accountService.mod(account);

        if (0 != count) return Result.success("修改成功", count);

        return Result.error("修改失败");
    }

    // 浏览所有的车票
    @RequestMapping("/viewAllTicket")
    public Result viewAllTicket() {

        List<Ticket> tickets = ticketService.searchAllTicket();

        if (!tickets.isEmpty()) return Result.success("查看成功", tickets);
        else return Result.error("查看失败");


    }


    @Autowired
    ChatRoomService chatRoomService;


    //    插入或重新进行一个聊天室
    @RequestMapping("/chating")
    public Result buildChatRoom(Chaters chaters) {

        Passenger passenger = chaters.getPassenger();
        Driver driver = chaters.getDriver();

//      得到聊天室，如果聊天室的id为null，就说明没有。此时才真正的插入。就返回原来的聊天室。
//        会在service层判断。

        ChatRoom chatRoom1 = chatRoomService.createChatRoom(passenger, driver);

        if (chatRoom1.getId() != null) {

            chatRoom1.setName(passenger.getName());

            return Result.success("进行聊天", chatRoom1);

        } else return Result.error("失败");


    }

}
