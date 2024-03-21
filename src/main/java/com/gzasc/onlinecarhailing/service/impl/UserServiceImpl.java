package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.UserMapper;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gzasc.onlinecarhailing.pojo.Account;

import java.util.List;

//用户服务层实现类
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


//    登录功能
    @Override
    public Account login(Account account) {

        List<Account> accountList = userMapper.selectAllUser(account);
        Account account1 = null;
        if(!accountList.isEmpty()) {

            account1 = accountList.get(0);

            account1.setPassword("");

            return account1;
        }

        else return account1;

    }

    //    注册功能
    @Override
     public Integer register(Account account){

        userMapper.insertAccount(account);

        return account.getId();

    }

//    验证数据库里是否存在相同的帐号

    @Override
    public Account isAccountExit(Account account) {

//        直接将帐号返回
        return userMapper.isAccountExit(account);
    }
    //    通过帐号ID删除帐号
    @Override
    public Integer removeAccount(Account account) {

        return userMapper.deleteAccount(account);
    }
//更改帐号的密码
    @Override
    public Integer modAccountPassword(Account account) {

        return userMapper.alterAccountPassword(account);
    }
}
