package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.AccountService;
import com.gzasc.onlinecarhailing.service.DriverSerivce;
import com.gzasc.onlinecarhailing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//用户都有的接口
@RestController
public class UserController {

    @Autowired
    AccountService accountService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    DriverSerivce driverSerivce;



//    注册成为乘客
    @RequestMapping("/register")
    public Result registerPassenger(Account account){

//        要先判断帐号是否存在
        if(accountService.search(account.getAccount())!=null){

//            帐号已经存在了，应该要修改帐号再注册
            return Result.error("帐号已经存在。");

        }else {
//            先注册了帐号
            Integer accountId = accountService.register(account);

            if (account.getIt() == 0){

                Passenger passenger = new Passenger();
                passenger.setAccount(account.getAccount());
                passenger.setAccountId(accountId);
                Integer pasengerId = passengerService.register(passenger);

                return Result.success("乘客注册成功", passengerService.search(pasengerId));

            } else if (account.getIt() == 1) {
                Driver driver = new Driver();
                driver.setAccount(account.getAccount());
                driver.setAccountId(accountId);
                Integer driverId = driverSerivce.register(driver);
                return Result.success("乘客注册成功", driverSerivce.search(driverId));
            }else return Result.error("注册失败");
        }

    }



    //    修改帐号密码
    @RequestMapping("/newPassword")
    public Result alterAccountPassword(Account account){

        Integer count = accountService.mod(account);

        if (0 != count) return Result.success("修改成功", count);

        return Result.error("修改失败");
    }



}
