package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

//乘客的类
@Data
@NoArgsConstructor
public class Passenger extends User{

//    乘客表的ID
    private Integer passengerId;

    //    更新时间
    private Date updateTime;

//    以下是在政府的标准里非必须的


////    帐号
//    private String account;
////    密码
//    private String password;


//    乘客的订单
    private List<Order> orders;

    //    自定义的地址说明，地址细节

    private String addressDetail;
}
