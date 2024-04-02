package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Order;
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

//    修改对应的乘客的id为null
    Integer updateAccountsPassengerIdToNullByAccountIds(List<Integer> accountIds);

//    通过帐号，查询一条
    public Account selectByAccount(String account);
//    查询通过id
    public List<Account> selectById( List<Integer> ids);
//


//    查询所有存在的。
    public List<Account> selectAll();

// 批量删除帐号，不过在删除帐号的同时，也要删除用户才行。
    Integer deleteAccountsById(List<Integer> ids);

}
