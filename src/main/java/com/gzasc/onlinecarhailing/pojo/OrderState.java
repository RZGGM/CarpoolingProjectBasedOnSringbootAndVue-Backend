package com.gzasc.onlinecarhailing.pojo;

// 订单状态
public class OrderState {

//    已取消
    public static final Integer CONCELED = -3;
//    等待确认的
    public static final Integer WAIT_CONFIRM = -4;
// 已经完成
    public static final Integer FINSHED = 1;
//    已经有司机
    public static final Integer HAS_DRIVER = 2;

//    乘客已取消
    public static final Integer PASSENGER_CONCEL = -1;
//    司机已取消
    public static final Integer DRIVER_CONCEL = -2;

}
