package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

import java.util.Date;

//司机的类
@Data
public class Driver extends User{

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

//    是否巡游出租汽车驾驶员
    private Integer taxiDriver;

//    网络预约出租汽车驾驶员资格证
    private NetworkDriverLicense networkDriverLicense;

//    报备日期
    private Date registerDate;
//    服务类型
    private Integer commercialType;


//    驾驶员合同（或协议）签署公司
    private String contractCompany;
//    合同（或协议）有效期起
    private Date contractOn;
//    合同（或协议）有效期止
    private Date contractOff;



//    更新时间
    private Date updateTime;


//    以下是在政府的标准里非必须的
////    帐号
//private String account;
//    //    密码
//    private String password;

}
