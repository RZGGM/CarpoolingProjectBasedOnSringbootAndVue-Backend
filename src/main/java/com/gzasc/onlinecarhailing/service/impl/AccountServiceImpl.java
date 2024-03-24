package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.AccountMapper;
import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

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
}
