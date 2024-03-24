package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.UserMapper;
import com.gzasc.onlinecarhailing.pojo.User;
import com.gzasc.onlinecarhailing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//用户服务层实现类
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


//    登录功能
    @Override
    public User login(User user) {

//        在数据库里查询用户使用的帐号和密码是否正确
        User user1 =  userMapper.seleceByAccount(user);

        if(user1!=null){

        }

        return user1;


    }

    //    注册功能
    @Override
     public Integer register(User user){

        userMapper.insertUser(user);

        return user.getId();

    }

//    验证数据库里是否存在相同的帐号

    @Override
    public User isUserExit(User user) {

//        直接将帐号返回
        return userMapper.isUserExit(user);
    }
    //    通过帐号ID删除帐号
    @Override
    public Integer removeUser(Integer id) {

        return userMapper.deleteUser(id);
    }
//更改帐号的密码
    @Override
    public Integer modUserPassword(User user) {

        return userMapper.alterUserPassword(user);
    }
}
