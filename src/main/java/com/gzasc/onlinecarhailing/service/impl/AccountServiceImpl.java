package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.AccountMapper;
import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    PassengerMapper passengerMapper;

    @Override
    public Integer register(Account account) {

        return accountMapper.insertAccount(account);
    }

    @Override
    public Integer remove(Integer id) {
        return accountMapper.deleteAccount(id);
    }

    @Override
    public Integer mod(Account newAccount) {

        return accountMapper.updateAccount(newAccount);
    }

    @Override
    public Account search(String account) {
        return accountMapper.selectByAccount(account);
    }

    @Override
    public List<Account> searchAll() {
        return accountMapper.selectAll();
    }

    @Override
    public Integer removeAccountsByIds(List<Integer> ids) {

//        先查询到数据库对应的表的数据，然后判断有无乘客信息或是司机信息，然后删除。
        List<Account> accountList = accountMapper.selectById(ids);

        for (Account account : accountList) {

            if (account.getDriverId() != null){
                driverMapper.deleteById(account.getDriverId());
            }
            if (account.getPassengerId() != null){
                passengerMapper.deleteById(account.getPassengerId());
            }

        }

        Integer countDeleteAccount = accountMapper.deleteAccountsById(ids);

        return  countDeleteAccount;

    }
    //     根据手机号查询帐号
    @Override
    public Account searchByPhond(String phone){

       return accountMapper.selectByPhone( phone);

    }
    @Override
    public Account searchByPhoneAndAccount(Account account){

        return accountMapper.selectByPhoneAndAccount(account);

    }
}
