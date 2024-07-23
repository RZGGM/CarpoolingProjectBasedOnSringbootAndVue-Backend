package com.gzasc.onlinecarhailing.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.gzasc.onlinecarhailing.pojo.*;

import java.util.Date;
import java.util.Objects;

public class OrderUtils {


    //    返回一个订单编号
    public static String createOrderCode() {
        String format = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        String numbers = RandomUtil.randomNumbers(5);

        return format + numbers;
    }

    //     基于乘客的拼车订单为司机创建订单
    public static Order onPassengerCreateJoinOrderToCreateForDriver(Order order, Integer driverId) {

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

    //    基于拼车订单来为自己生成订单
    public static Order onDriverCreateJoinOrderToCreateForUser(Order order, Integer userId) {

//        传入的order是拼车订单，不是另一个用户接取拼车订单生成的订单，不过只有乘客在接取司机发起的拼车订单时才会出现这种情况。

        Order order1 = new Order();
//        设置创建者的类型为：不是拼车订单的另一个类型
        if (Objects.equals(order.getCreateUserType(), UserType.PASSENGER)) {
            order1.setCreateUserType(UserType.DRIVER);
        } else order1.setCreateUserType(UserType.PASSENGER);
//        设置订单的所有者为接受拼车订单的用户
        order1.setOwnerId(userId);
//        设置订单的目的地和起点和拼车订单和一致。
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
//        绑定另一个 基于拼车订单 生成的订单 的编号，这个属性得在得到另一个用户生成的订单时设置。
//    order1.setOtherId(order.getOrderId());


        return order1;
    }

    //    基于司机的拼车订单为乘客生成订单
    public static Order onDriverCreateJoinOrderToCreateForPassenger(Order driverCreateJoinOrder, Order passengerOrder) {

//        传入的order是拼车订单，不是另一个用户接取拼车订单生成的订单，不过只有乘客在接取司机发起的拼车订单时才会出现这种情况。

        Order order1 = new Order();
//        设置创建者的类型为：不是拼车订单的另一个类型
        if (Objects.equals(driverCreateJoinOrder.getCreateUserType(), UserType.PASSENGER)) {
            order1.setCreateUserType(UserType.DRIVER);
        } else order1.setCreateUserType(UserType.PASSENGER);

//        设置乘客的id为自己
        order1.setPassengerId(passengerOrder.getPassengerId());

//        设置订单的所有者为接受拼车订单的用户
        order1.setOwnerId(passengerOrder.getOwnerId());
//        设置订单的目的地和起点和拼车订单和一致。
        order1.setDepartureAddress(driverCreateJoinOrder.getDepartureAddress());
        order1.setDestinationAddress(driverCreateJoinOrder.getDestinationAddress());
//        设置订单的类型为等待出发
        order1.setState(OrderState.WAIT_DEPART);
//        设置订单的类型为：
        order1.setOrderType(OrderType.BASE_ON_DRIVER_CREATE_JOIN_ORDER_CREATE_TO_PASSENGER);
//        为订单分配一个编号
        order1.setOrderId(OrderUtils.createOrderCode());
//        价格
        order1.setPrice(passengerOrder.getPrice());
//        出行的人数
        order1.setPassengerCount(passengerOrder.getPassengerCount());
// 设置司机的id
        order1.setDriverId(driverCreateJoinOrder.getDriverId());
//        设置所有者的id
        order1.setOwnerId(passengerOrder.getOwnerId());

//        绑定另一个 基于拼车订单 生成的订单 的编号，这个属性得在得到另一个用户生成的订单时设置。
//    order1.setOtherId(order.getOrderId());


        return order1;
    }


    //    基于司机的拼车订单为乘客生成的订单生成司机的订单。
    public static Order onPassengerOrderToCreateForDriver(Order driverCreateJoinOrder, Order passengerOrder) {


        Order order = new Order();
//        设置类型
        order.setOrderType(OrderType.BASE_ON_DRIVER_CREATE_JOIN_ORDER_CREATE_TO_DRIVER);
//        设置司机的id为自己的
        order.setDriverId(driverCreateJoinOrder.getDriverId());
//        设置备注为乘客的备注
        order.setTips(passengerOrder.getTips());
//        设置地址
        order.setDestinationAddress(driverCreateJoinOrder.getDestinationAddress());
        order.setDepartureAddress(driverCreateJoinOrder.getDepartureAddress());
//        设置乘客的id为：
        order.setPassengerId(passengerOrder.getPassengerId());

//        设置价格
        order.setPrice(passengerOrder.getPrice());
//        设置用户id
        order.setPassengerId(passengerOrder.getPassengerId());
//        设置对应的订单的编号
        order.setOtherId(passengerOrder.getOrderId());
//          设置最大人数
        order.setManCount(-1);
//        设置状态为：等待出行
        order.setState(OrderState.WAIT_DEPART);
//        设置所有者的id
        order.setOwnerId(driverCreateJoinOrder.getOwnerId());
//        设置所有者的类型
        order.setCreateUserType(driverCreateJoinOrder.getCreateUserType());
//        设置自己的订单编号
        order.setOrderId(OrderUtils.createOrderCode());
//        设置乘客人数
        order.setPassengerCount(passengerOrder.getPassengerCount());

        return order;
    }


    //     基于乘客的拼车订单为乘客创建订单，感觉这个方法没有什么用。
    public static Order onPassengerCreateJoinOrderToCreateForPassenger(Order order, Integer driverId) {

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
//        设置订单的状态为等待出发
        order1.setState(OrderState.WAIT_DEPART);
//        设置订单的类型为：先设置和发起拼单的人的类型一致吧。
        order1.setOrderType(order.getOrderType());
//


        return order1;
    }


}
