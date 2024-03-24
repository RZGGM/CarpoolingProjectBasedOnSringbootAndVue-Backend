package com.gzasc.onlinecarhailing.service;

//用户服务层

import com.gzasc.onlinecarhailing.pojo.User;

public interface UserService {


//    登录
    public User login(User user);
//    帐号注册
    public Integer register(User user);
//    帐号注册前检查帐号是否存在
    public User isUserExit(User user);
//    通过帐号ID删除帐号
    public Integer removeUser(Integer id);
//    更改帐号密码
    public Integer modUserPassword(User user);


}
