package com.gzasc.onlinecarhailing.Mapper;


import com.gzasc.onlinecarhailing.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//操作用户数据库的
@Mapper
public interface UserMapper {

//    查询所有的用户，管理员用来管理所有用户的基础
    public List<User> selectAllUser();
//    注册帐号
    public void insertUser(User user);
//    检查帐号是否存在
    public User isUserExit(User user);
    //    通过帐号ID删除帐号
    public Integer deleteUser(Integer id);
//    修改密码
    public Integer alterUserPassword(User user);
//    登录功能，通过帐号查询用户
    public User seleceByAccount(User user);


}
