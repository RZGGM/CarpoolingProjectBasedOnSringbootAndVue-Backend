package com.gzasc.onlinecarhailing.controller;


import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//帐号接口的定义，不过对帐号的接口不应该定义在这
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;


//    管理员后台修改帐号信息，如改密码什么的。

    public Result editAccount(Account account){

        Integer count = accountService.mod(account);

        if (0 != count) return Result.success("修改成功", count);

        return Result.error("修改失败");

    }

//    管理员后台，删除帐号

    public Result deleteAccountsById(List<Integer> ids){


        Integer countAccount = accountService.removeAccountsByIds(ids);
        if (countAccount > 0) return Result.success("成功");
        else return Result.error("删除失败");

    }



}
