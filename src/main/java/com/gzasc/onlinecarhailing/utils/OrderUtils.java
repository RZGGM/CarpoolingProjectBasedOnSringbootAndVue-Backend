package com.gzasc.onlinecarhailing.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.OrderState;
import com.gzasc.onlinecarhailing.pojo.UserType;

import java.util.Date;

public class OrderUtils {





//    返回一个订单编号
    public static String createOrderCode(){
        String format = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        String numbers = RandomUtil.randomNumbers(5);

        return  format + numbers;
    }
    //     基于乘客的拼车订单为司机创建订单
    public static Order onPassengerCreateJoinOrderToCreateForDriver(Order order, Integer driverId){

        Order order1 = new Order();

//        设置创建者的类型为司机
        order1.setCreateUserType(UserType.DRIVER);
//        设置订单的所有都为司机
        order1.setOwnerId(driverId);
//        设置订单的目的地和起点和乘客和一致。
        order1.setDepartureAddress(order.getDepartureAddress());
        order1.setDestinationAddress(order.getDestinationAddress());
//        设置订单的类型为等待出发
        order1.setState(OrderState.WAIT_DEPART);
//        设置订单的类型为：先设置和发起拼单的人的类型一致吧。
        order1.setOrderType(order.getOrderType());
//        为订单分配一个编号
        order1.setOrderId(OrderUtils.createOrderCode());
//        价格
        order1.setPrice(order.getPrice());
//        绑定乘客的的订单的编号
        order1.setOtherId(order.getOrderId());


        return order1;
    }
    //     基于乘客的拼车订单为乘客创建订单，感觉这个方法没有什么用。
    public static Order onPassengerCreateJoinOrderToCreateForPassenger(Order order, Integer driverId){

        Order order1 = new Order();

//        设置创建者的类型为：乘客
        order1.setCreateUserType(UserType.PASSENGER);
//        设置订单的所有者的id，在前端传过来的参数，为订单，订单中就包含了所有者的id。
//        司机的id只是为了设置司机的id。也可以直接在前端传来。
//        当乘客发起一个拼单时，并且还设置为接受拼单时，
//        ####应该是控制接取了这个订单的司机是否还能再接取订单，
//        也就是应该在司机的类上再加上一个属性，控制司机是否能再接取乘客的订单。
        order1.setOwnerId(order.getOwnerId());
//        设置司机的id
        order1.setDriverId(order.getDriverId());
//        设置订单的目的地和起点和乘客和一致。
        order1.setDepartureAddress(order.getDepartureAddress());
        order1.setDestinationAddress(order.getDestinationAddress());
//        设置订单的类型为等待出发
        order1.setState(OrderState.WAIT_DEPART);
//        设置订单的类型为：先设置和发起拼单的人的类型一致吧。
        order1.setOrderType(order.getOrderType());


        return order1;
    }
}
