package com.gzasc.onlinecarhailing.pojo;

//订单的类型
public class OrderType {

    //用来区分发起拼单的，是哪一方发起的：
// 乘客：1， 司机：2
//    乘客等待司机的：11， 司机等待乘客的：21
//    乘客发起的，乘客和司机都有的，等待拼车乘客的：111，不等待的：112
//    ，  司机发起的，已经有乘客，等待乘客的：211，不等待的：212
//  不发起拼单的乘客查看可以拼单的订单：？不需要。
    //    类型为：乘客等待司机的订单
    public static final Integer PASSENGER_WAIT_DRIVER = 1;
    //类型为：可拼车的订单
    public static final Integer DRIVER_AND_PASSENGER_WAIT_OTHERPASSENGER = 2;
    //类型为：满员的订单
    public static final Integer PASSENGER_MAXED = 3;
//    司机发起的，等待乘客的订单
    public static final Integer DRIVER_WAIT_PASSENGER = 4;




}
