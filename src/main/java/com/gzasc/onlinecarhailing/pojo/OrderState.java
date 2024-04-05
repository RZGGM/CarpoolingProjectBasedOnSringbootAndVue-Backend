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

//    已经评价了
    public static final Integer APPRAISED = 3;

//    乘客已取消
    public static final Integer PASSENGER_CONCEL = -1;
//    司机已取消
    public static final Integer DRIVER_CONCEL = -2;

}
