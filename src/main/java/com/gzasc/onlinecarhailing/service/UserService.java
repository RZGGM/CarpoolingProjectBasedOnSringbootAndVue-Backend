package com.gzasc.onlinecarhailing.service;

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
