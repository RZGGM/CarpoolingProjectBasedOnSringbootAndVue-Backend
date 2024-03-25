package com.gzasc.onlinecarhailing.service;


import com.gzasc.onlinecarhailing.pojo.Account;

import java.util.List;

//帐号的操作
 public interface AccountService {

//    注册帐号
     Integer register(Account account);
//    删除，通过id
     Integer remove(Integer id);
//    修改
     Integer mod(Account newAccount);
//    根据帐号查询
     Account search(String account);
//    查询所有
     List<Account> searchAll();
//     通过id批量删除帐号，同时删除用户
     Integer removeAccountsByIds(List<Integer> ids);

}
