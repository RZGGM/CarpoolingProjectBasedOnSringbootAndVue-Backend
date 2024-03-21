package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.Mapper.UserMapper;

import com.gzasc.onlinecarhailing.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//用户服务层

public interface UserService {


//    登录
    public Account login(Account account);
//    帐号注册
    public Integer register(Account account);
//    帐号注册前检查帐号是否存在
    public Account isAccountExit(Account account);
//    通过帐号ID删除帐号
    public Integer removeAccount(Account account);
//    更改帐号密码
    public Integer modAccountPassword(Account account);


}
