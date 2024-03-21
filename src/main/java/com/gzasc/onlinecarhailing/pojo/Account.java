package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

//帐号
@Data
public class Account {

//    id
    private int id;
//    帐号
    private String account;
//    密码
    private String password;

//    新密码，当修改密码时，才用到这个属性
    private String newPassword;

//    有两个参数的构造
    public Account(String account, String password){

        this.account = account;
        this.password = password;

    }
}
