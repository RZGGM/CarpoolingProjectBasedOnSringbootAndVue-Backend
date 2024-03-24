package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//司机的类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver{

//    司机也是一张和用户独立的表，也得有一个参数接收表的ID
    private Integer driverId;
//    出生日期
    private Date driverBirthday;
//    民族
    private String driverNation;
//    通信地址
    private String driverAddress;
//    驾驶证
    private DriverLicense driverLicense;
    //    更新时间
    private Date updateTime;
    //    手机号
    private String phone;
    //    性别
    private String gender;
    //    状态
    private Integer state;
    //    姓名
    private String name;
    //    帐号id
    private Integer accountId;
//    帐号
    private String account;

}
