package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

import java.util.Date;

//乘客订单
@Data
public class PassengerBuyTicketOrder {

//      id
    private Integer id;
//    订单金额，这个在乘客的票里有。
    private Integer price;
//    创建时间
//    订单发起时间，这个每个乘客应该是不一样的。
    private Date createTime;
    //    订单完成时间，这个每个乘客应该是不一样的。
    private Date finishTime;
//    司机核验码，在上车时报给司机听，开始行程。
    private Integer verifyCode;

//    状态，0为未支付，1为已支付，2为等待出行，3为出行中，4为已经完成的订单，
//    -1为已取消订单。当订单为2状态时或是3状态时，无法取消订单。
    private Integer status;
//    订单在2状态时，会显示司机的手机号
    private Integer phone;


}
