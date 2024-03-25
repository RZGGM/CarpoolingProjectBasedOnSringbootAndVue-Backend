package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//帐号
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

//    id
    private Integer id;
//    帐号
    private String account;
//    密码
    private String password;
//    帐号的身份，就是对应的用户表的里的数据的id。
    private Integer driverId;
    private Integer passengerId;
    private Integer managerId;
//    注册时要用到标识，当它为0就是乘客，当它为1就是司机，当它为2就是管理员。
    private Integer it;


}
