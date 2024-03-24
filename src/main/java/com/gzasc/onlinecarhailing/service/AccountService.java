package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.Mapper.AccountMapper;
import com.gzasc.onlinecarhailing.pojo.Account;

import java.util.List;

//帐号的操作
public interface AccountService {

//    注册帐号
    public Integer register(Account account);
//    删除，通过id
    public Integer remove(Integer id);
//    修改
    public Integer mod(Account newAccount);
//    根据帐号查询
    public Account search(String account);
//    查询所有
    public List<Account> searchAll();

}
