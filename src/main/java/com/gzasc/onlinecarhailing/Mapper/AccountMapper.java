package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对帐号表的操作
@Mapper
public interface AccountMapper {

//    增加
    public Integer insertAccount(Account account);
//    删除byId。
    public Integer deleteAccount(Integer id);
//    修改
    public Integer updateAccount(Account newAccount);
//    通过帐号，查询一条
    public Account selectByAccount(String account);
//    查询所有存在的。
    public List<Account> selectAll();

}
