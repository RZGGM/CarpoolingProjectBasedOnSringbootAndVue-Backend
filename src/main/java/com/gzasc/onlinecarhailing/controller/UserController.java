package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.*;
import com.gzasc.onlinecarhailing.utils.CountPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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

        if (account == null) return Result.error("帐号为空，登录失败。");

        String password = account.getPassword();

        log.info(account.toString());

// 传入的帐号的类型
        Integer type = account.getIt();

        Account account1 = accountService.search(account.getAccount());

        if (type == null || type > UserType.OFFICIAL) return Result.error("帐号类型为Null或是没有此类型，登录失败。");

        else {
            if (account1 == null) return Result.error("此帐号不存在，或是密码错误，所以登录失败");

            account1.setIt(account.getIt());

            if (type.equals(UserType.PASSENGER) && null != account1.getPassengerId()) {
                account1.setIt(1);
                return Result.success("乘客登录成功", account1);
            } else if (type.equals(UserType.DRIVER) && null != account1.getDriverId()) {
                account1.setIt(2);
                return Result.success("司机登录成功", account1);
            } else if (type.equals(UserType.OFFICIAL) && null != account1.getManagerId()) {
                account1.setIt(3);
                return Result.success("管理员登录成功,", account1);
            } else return Result.error("帐号类型错误，登录失败");
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

            if (Objects.equals(account.getIt(), UserType.PASSENGER)) {
//            先注册了帐号
                accountService.register(account);
                Integer accountId = account.getId();
                Passenger passenger = new Passenger();
                passenger.setAccount(account.getAccount());
                passenger.setAccountId(accountId);
                passengerService.register(passenger);
                Integer passengerId = passenger.getPassengerId();

                account.setPassengerId(passengerId);

                accountService.mod(account);

                return Result.success("乘客注册成功", passengerService.search(passengerId));

            } else if (Objects.equals(account.getIt(), UserType.DRIVER)) {
                //            先注册了帐号
                accountService.register(account);
                Integer accountId = account.getId();
                Driver driver = new Driver();
                driver.setAccount(account.getAccount());
                driver.setAccountId(accountId);
//                再注册成为司机到司机表中。

                driverSerivce.register(driver);
                Integer driverId = driver.getDriverId();

                account.setDriverId(driverId);
//   注册成功后，要绑定司机或是乘客的表的id到帐号表。一个帐号可以有两个身份。
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

    // 浏览所有的车票，用户的
    @RequestMapping("/viewAllTicket")
    public Result viewAllTicket() {

        List<Ticket> tickets = ticketService.searchAvailableTicket();
        if (null == tickets) return Result.error("获取的票是null");
        if (!tickets.isEmpty()) return Result.success("查看成功", tickets);


        else return Result.success("获取票成功，但可惜数据库里没有票的数据。");


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

    //    计算发出的接单的预计价格
    @RequestMapping("/countPriceOfAddress")
    public Result countPriceOfAddress(@RequestParam("departureAddress") String departureAddress, @RequestParam("destinationAddress") String destinationAddress) {

        if (null == departureAddress || null == destinationAddress) return Result.error("传入的地址有空值");

        CountPrice countPrice = new CountPrice();

        return Result.success("根据地址计算好了价格", countPrice.countPrice(departureAddress, destinationAddress));


    }
    //    计算发出的接单的预计价格
    @RequestMapping("/countPriceOfPassengerCount")
    public Result countPriceOfPassengerCount(Integer passengerCount, Integer orderPrice) {

        if (passengerCount == 0) return Result.error("人数为0");

        CountPrice countPrice = new CountPrice();

        return Result.success("根据人数计算好了价格", countPrice.countPriceOfPassengerCount(passengerCount, orderPrice));


    }

//    登录之后，根据身份和身份对应的id找到对应的身份信息
    @RequestMapping("/viewStanding")
    public Result viewStanding(@RequestBody Account account){

        if (account == null) return Result.error("传入的帐号为null");

        if (Objects.equals(account.getIt(), UserType.PASSENGER)){
            return Result.success("找到乘客信息成功了，", passengerService.search(account.getPassengerId()));
        } else if (account.getIt().equals(UserType.DRIVER)) {

            return Result.success("找到司机的身份信息成功", driverSerivce.search(account.getDriverId()));

        }else return Result.success("管理员没有身份信息");


    }




}
