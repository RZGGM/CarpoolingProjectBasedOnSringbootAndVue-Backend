package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

// 订单状态
@Data
public class OrderState {

//    已取消
    public static final Integer CONCELED = -3;

//    等待支付的官方的订单
    public static final Integer WAIT_PAY = -5;

//    等待确认的
    public static final Integer WAIT_CONFIRM = -4;
// 已经完成
    public static final Integer FINSHED = 1;
//    已经有司机
    public static final Integer HAS_DRIVER = 2;

//    乘客发起的拼单订单等着司机的。
    public static final Integer PASSENGER_CREATE_SHARE_BILL_WAIT_DRIVER = 4;
//    司机发起的拼单等待乘客
    public static final Integer DRIVER_CREATE_SHARE_BILL_WAIT_PASSENGER = 5;

//    拼单的订单在有司机和乘客后，将订单的状态设置为等待出发，至于还能拼单就是另一个订单的状态设置问题了。
    public static final Integer WAIT_DEPART = 6;

//    已经评价了
    public static final Integer APPRAISED = 3;

//    乘客已取消
    public static final Integer PASSENGER_CONCEL = -1;
//    司机已取消
    public static final Integer DRIVER_CONCEL = -2;

}
