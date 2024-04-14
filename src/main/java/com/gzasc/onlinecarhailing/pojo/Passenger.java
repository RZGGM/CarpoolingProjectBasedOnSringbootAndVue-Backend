package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//乘客的类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger{

//    乘客表的ID
    private Integer passengerId;

//    以下是在政府的标准里非必须的
    //    手机号
    private String phone;
    //    性别
    private String gender;
    //    状态
    private Integer state;
    //    姓名
    private String name;
    //    帐号
    private String account;

//    //    乘客的订单，用订单里记录的用户的id来得到。
//    private List<Order> orders;
    //    帐号
    private Integer accountId;
    //    更新时间
    private Date updateTime;



}
