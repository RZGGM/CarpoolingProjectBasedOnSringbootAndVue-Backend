package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//订单类，应该有两种订单类，一个是司机看的，一个是乘客看的。
// 司机一个订单对应着乘客一个订单。这两个订单间有关联。
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

//ID
    private Integer id;

// 订单编号和ID一样是唯一的。
    private String orderId;

//    订单创建者的id
    private Integer ownerId;
//    订单创建者的类型
    private Integer createUserType;
//    ***拼单订单的话，一个订单取消另一个订单也要取消才对。
    private String otherId;

//    订单发起时间，这个每个乘客应该是不一样的。
    private Date createTime;

//    订单完成时间，这个每个乘客应该是不一样的。
    private Date finishTime;

//    下面的要一个，应该用一个类来接收它，这样就是可以多个用户进行修改了。不过要同一对象才行。
//    不过现在好像也是了，都是存放在堆的数据。
//    司机，-1代表没有对应的司机id，
    private Integer driverId = -1;
//    可同行的人数，或者说是订单最大接取人数。司机发起的订单，就是乘客的数量，
//    乘客发起的订单，就是是否可以拼车，只有
    private Integer manCount;
//    乘客， -1，乘客也一样。
    private Integer passengerId = -1;
//    备注
    private String tips;
//    价格
    private float price;


//用来区分发起拼单的，是哪一方发起的：
// 乘客：1， 司机：2
//    乘客等待司机的：11， 司机等待乘客的：21
//    乘客发起的，乘客和司机都有的，等待拼车乘客的：111，不等待的：112
//    ，  司机发起的，已经有乘客，等待乘客的：211，不等待的：212
//
    private Integer orderType;
//    订单的状态，乘客订的官方的票应该有完成订单，和已经取消的订单，
//    乘客发起的，应该有等待司机和等待拼单
//    司机发起的，应该有等待接单。
    private Integer state;
//    订单对应的票的id，其它是订单一些其它信息。在购物系统里就是商品的，在这个出行的，就是行程的。
    private Integer ticketId;

//    起点
    private String departure;
// 终点
    private String destination;
}
