package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
// 订单对应的评论的id
    private Integer appraiseId;

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
//    用这个来判断买的是票，还是拼单订单，-1就是官方的票。
//    司机的话就表示是可以拼单的乘客数量 。
//    但是这样不好分开，万一要查询怎么办。还是弄多一个属性来判断吧。
    private Integer manCount = -1;
//    默认的-1就表示是非拼单订单。
    private Integer joinOrderCanJoin = com.gzasc.onlinecarhailing.pojo.JoinOrderCanJoin.NON_JOIN_ORDER;

//    滴滴有个乘客人数，也加上去吧。这个是乘客发出拼单时选择的，默认是1，毕竟发出拼单就是至少要一个人，根据乘客的选择来变化，
//    这个是乘客用的，司机根本不用这个属性。
    private Integer passengerCount = 1;

//    乘客， -1，乘客也一样。不知道有什么用，不用买票的话，就用它来放乘客的id吧，不过其实订单的创建者的id也是乘客的id。
    private Integer passengerId = -1;
//    备注
    private String tips;
//    价格
    private float price;


//用来区分发起拼单的，是哪一方发起的：
//    0：乘客购买官方的票的订单。
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
    private String departureAddress;
// 终点
    private String destinationAddress;

//    联系用的手机号
    private String phone;
//    评价
//    这个属性其实没用到，只是方便我画类图
    private Appraise appraise;





}
