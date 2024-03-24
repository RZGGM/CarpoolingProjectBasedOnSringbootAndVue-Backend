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


}
