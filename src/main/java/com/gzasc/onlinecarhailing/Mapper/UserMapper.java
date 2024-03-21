package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//操作用户数据库的
@Mapper
public interface UserMapper {

//    查询根据帐号查询用户的信息，用来登录的
    public List<Account> selectAllUser(Account account);
//    注册帐号
    public void insertAccount(Account account);
//    检查帐号是否存在
    public Account isAccountExit(Account account);
    //    通过帐号ID删除帐号
    public Integer deleteAccount(Account account);
//    修改密码
    public Integer alterAccountPassword(Account account);


}
