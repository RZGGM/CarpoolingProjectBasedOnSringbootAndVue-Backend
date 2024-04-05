package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

//订单的类型
@Data
public class OrderType {

    //用来区分发起拼单的，是哪一方发起的：
// 乘客：1， 司机：2
//    乘客等待司机的：11， 司机等待乘客的：21
//    乘客发起的，乘客和司机都有的，等待拼车乘客的：111，不等待的：112
//    ，  司机发起的，已经有乘客，等待乘客的：211，不等待的：212
//  不发起拼单的乘客查看可以拼单的订单：0，只是用来作用参数传递的，不会保存到数据库里。

    //    满员的订单：-1
//    //  不发起拼单的乘客查看可以拼单的订单：0，只是用来作用参数传递的，不会保存到数据库里。
    public static final Integer PASSENGER_CAN_JOIN = 0;

    //    乘客发起的：
    public static final Integer PASSENGER_CREATE_ORDER = 1;

    //    类型为：乘客等待司机的订单
    public static final Integer PASSENGER_WAIT_DRIVER = 11;

    //类型为：乘客发起的，可拼车的订单
    public static final Integer PASSENGER_AND_DRIVER_WAIT_OTHERPASSENGER = 111;
    //    不等待的
    public static final Integer PASSENGR_AND_DRIVER_NOWAIT = 112;
    //    司机发起的
    public static final Integer DRIVER_CREATE = 2;
    //    司机发起的，等待乘客的订单
    public static final Integer DRIVER_WAIT_PASSENGER = 21;
    //    司机发起的，还能再有乘客的
    public static final Integer DRIVER_AND_PASSENGER_WAIT_OTHERPASSENGER = 211;
    //    司机发起的，不等待的（其实应该是满客了的）
    public static final Integer DRIVER_AND_PASSENGER_NOWAIT = 212;


    //类型为：满员的订单
    public static final Integer PASSENGER_MAXED = -1;

// 类型为：乘客购买的官方的票的订单
    public static final Integer OFFICIAL_TICKET = 400;


}
