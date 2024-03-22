package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//订单类，应该有两种订单类，一个是司机看的，一个是乘客看的。
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

//ID
    private Integer id;
//    订单类型
    private Integer typeCode;
// 订单编号和ID一样是唯一的。
    private String orderId;
//    订单发起时间，这个每个乘客应该是不一样的。
    private Date createTime;
//    订单不再接受新的接单用户的时间
    private Date endTime;
//    订单完成时间，这个每个乘客应该是不一样的。
    private Date finishTime;

//    下面的要一个，应该用一个类来接收它，这样就是可以多个用户进行修改了。不过要同一对象才行。
//    不过现在好像也是了，都是存放在堆的数据。
//    司机
    private Driver driver;
//    可同行的人数
    private Integer manCount;
//    乘客
    private List<Passenger> passengers;
//    备注
    private String tips;

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }
}
