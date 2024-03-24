package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//登录验证的控制层，乘客、司机、管理员统一都有的功能。

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    登录功能
    @RequestMapping("/login")
    public Result login(Account account){

        Account account1 = userService.login(account);

        if(null != account1){


            account1.setPassword("");

            return Result.success("登录成功", account1);
        }

        return Result.error("登录失败");


    }

//    注册功能
    @RequestMapping("/register")
    public Result register(Account account){

//        不存在时，才会进行注册。
        if(null == userService.isAccountExit(account)){

            Integer id = userService.register(account);

            return Result.success();
        }

        return Result.error("注册失败，帐号已存在。");



    }

    //    通过帐号ID删除帐号，一次删除一个。
    @RequestMapping("/deleteAccount")
    public Result deleteAccountById(Account account){

        if(userService.removeAccount(account) > 0){

            return Result.success("删除成功", account);
        }

        return Result.error("删除失败");
    }
//    修改密码
    @RequestMapping("/alterPassword")
    public Result editAccountPassword(Account account, String newPassword){

        Integer modAccountNum = userService.modAccountPassword(account);
        account.setPassword("");
        account.setNewPassword("#");
        if(modAccountNum > 0) return Result.success("修改成功",account);

        return Result.error("修改失败");
    }

}
