package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对帐号表的操作
@Mapper
public interface AccountMapper {

//    增加
     Integer insertAccount(Account account);
//    删除byId。
     Integer deleteAccount(Integer id);
//    修改
     Integer updateAccount(Account newAccount);

//    修改对应的乘客的id为null
    Integer updateAccountsPassengerIdToNullByAccountIds(List<Integer> accountIds);

//    通过帐号，查询一条
     Account selectByAccount(String account);
//    查询通过id
     List<Account> selectById( List<Integer> ids);
// 根据帐号和手机号查询帐号的信息
    Account selectByPhoneAndAccount(Account account);
//    根据手机号查询帐号
    Account selectByPhone(String phone);

//    查询所有存在的。
     List<Account> selectAll();

// 批量删除帐号，不过在删除帐号的同时，也要删除用户才行。
    Integer deleteAccountsById(List<Integer> ids);

}
