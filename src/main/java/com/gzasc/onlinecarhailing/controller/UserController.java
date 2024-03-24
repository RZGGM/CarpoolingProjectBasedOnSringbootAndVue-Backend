package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.pojo.User;
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
    public Result login(User user){

        User user1 = userService.login(user);

        if(null != user1){


            user1.setPassword("");

            return Result.success("登录成功", user1);
        }

        return Result.error("登录失败");


    }

//    注册功能
    @RequestMapping("/register")
    public Result register(User user){

//        不存在时，才会进行注册。
        if(null == userService.isUserExit(user)){

            Integer id = userService.register(user);

            return Result.success();
        }

        return Result.error("注册失败，帐号已存在。");



    }

    //    通过帐号ID删除帐号，一次删除一个。
    @RequestMapping("/deleteUser")
    public Result deleteUserById(User user){

        if(userService.removeUser(user.getId()) > 0){

            return Result.success("删除成功", user);
        }

        return Result.error("删除失败");
    }
//    修改密码
    @RequestMapping("/alterPassword")
    public Result editUserPassword(User user){

        Integer modUserNum = userService.modUserPassword(user);

        if(modUserNum > 0) return Result.success("修改成功",user);

        return Result.error("修改失败");
    }

}
