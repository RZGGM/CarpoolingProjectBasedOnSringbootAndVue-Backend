package com.gzasc.onlinecarhailing.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class DriverController {

    @Autowired
    PassengerService passengerService;
    @Autowired
    CarService carService;
    @Autowired
    OrderService orderService;
    @Autowired
    DriverSerivce driverSerivce;
    @Autowired
    DriverLicenseService driverLicenseService;
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    MessageService messageService;


    //     查看自己的驾驶证
    @RequestMapping("/driver/viewDriverLicense")
    public Result viewDriverLicenseByDriverLicenseId(Integer driverLicenseId) {
        if (driverLicenseId == null) return Result.error("传入的驾驶证的id为null");

        DriverLicense driverLicense = driverLicenseService.searchDriverLicenseById(driverLicenseId);

        if (driverLicense == null) return Result.error("失败，找到的驾驶证为null");

        return Result.success("成功找到了驾驶证", driverLicense);


    }

    //  通过所有者的id，查询车
    @RequestMapping("/driver/myCar")
    public Result seekCars(Integer ownerId) {

        List<Car> cars = carService.searchByOwnerId(ownerId);


        if (!cars.isEmpty()) return Result.success("查询成功", cars);
        else return Result.error("没有车");

    }

    //    查看自己的订单
    @RequestMapping("/driver/orders")
    public Result seekOrders(Integer createUserType, Integer driverId) {

//        List<Order> orders = orderService.selectBySelfId(driverId);

        List<Order> orders = orderService.searchOrdersByCreateUserTypeAndOwnerId(createUserType, driverId);
        if (orders.isEmpty()) return Result.success("没有订单");
        else return Result.success("查询成功", orders);

    }

    //    查看自己的所有订单，但是是分页的
    @RequestMapping("/driver/viewOrdersSplit")
    public  Result viewAllOrdersSplit(@RequestBody  OrderQuery orderQuery){

        PageHelper.startPage(orderQuery.getPageNum(), orderQuery.getPageSize());
        List<Order> orders = orderService.searchOrdersByCreateUserTypeAndOwnerId(
                orderQuery.getCreateUserType(),
                orderQuery.getCreateUserId()
        );
        PageInfo<Order> pageInfo = new PageInfo<>(orders);//分页信息

        return Result.success("查看成功", pageInfo);
    }

    //查看可以接单的订单
    @RequestMapping("/driver/viewCanAcceptOrder")
    public Result viewCanAcceptOrder() {

        List<Order> orders = orderService.searchOrdersByOrderState(OrderState.PASSENGER_CREATE_SHARE_BILL_WAIT_DRIVER);

        if (orders == null) return Result.error("查询乘客发起的拼单订单失败");

        if (!orders.isEmpty()) return Result.success("查看乘客发起的拼单订单成功", orders);

        else return Result.success("没有可以接受乘客发起的拼单订单");

    }

    //    保存修改后的个人信息
    @RequestMapping("/driver/saveStanding")
    public Result saveStanding(@RequestBody Driver driver) {

        if (driver == null) return Result.error("传入的司机的身份是空的");

        Integer count = driverSerivce.mod(driver);

        if (count > 0) return Result.success("更改司机的信息成功");

        else return Result.error("更改司机的信息失败");


    }

    //    接单
    @RequestMapping("/driver/acceptOrder")
    public Result acceptOrder(String passengerOrderId, Integer driverId) {

        if (passengerOrderId == null || driverId == null) return Result.error("错误，订单或是司机的id为null");

//        根据乘客订单的id，来接受订单
        Integer count = driverSerivce.addOrderToDriver(passengerOrderId, driverId);

        if (Objects.equals(count, OrderState.HAS_DRIVER)) return Result.error("失败，已经有司机接单了。");
        else return Result.success("成功", count);

    }

    //    发出拼单
    @RequestMapping("/driver/createShareTheBill")
    public Result createShareTheBill(@RequestBody Order order) {

        if (null == order) return Result.error("订单为null");

        if (null == order.getOrderType()) return Result.error("订单类型为null");

//        判断订单是不是拼单类型的订单，如果不是就return。
        if (JoinOrderCanJoin.NON_JOIN_ORDER.equals(
                order.getJoinOrderCanJoin()
        )) return Result.error("并非是拼单订单。");

//        判断订单类型。
        if (OrderType.PASSENGER_CREATE_ORDER.equals(order.getOrderType())) {
//如果是乘客发起的订单就进来
            Integer count = orderService.addJoinOrderToPassenger(order);

            if (count > 0) return Result.success("创建订单成功");

            else return Result.error("创建订单失败");
        } else if (OrderType.DRIVER_CREATE.equals(order.getOrderType())) {
//            如果是司机发起的就进入到这
            Integer count = orderService.addJoinOrderToDriver(order);

            if (count > 0) return Result.success("创建订单成功");

        } else return Result.error("订单类型错误");
        return Result.error("创建订单失败，因为类型不符合。");
    }

    @RequestMapping("/driver/cancelOrder")
    public Result cancelOrder(@RequestBody String orderId) {

        Integer count = orderService.modOrderByOrderId(orderId);

        if (count > 0) return Result.success("司机取消订单成功");
        else return Result.error("司机取消订单失败");

    }


    //    查看乘客自己的所有的聊天室
    @RequestMapping("/driver/viewAllChatRooms")
    public Result viewAllChatRooms(Integer driverId){

        List<ChatRoom> chatRooms = chatRoomService.searchChatRoomsByDriverId(driverId);

        if (chatRooms == null) return Result.error("没有聊天室");

        if (chatRooms.isEmpty()) return Result.error("聊天室的列表为空，没有找到聊天室");

        else return Result.success("这就是所有的聊天室了。", chatRooms);


    }

    //    根据传入的聊天室的id找信息
    @RequestMapping("/driver/viewAllMessagesByChatRoomId")
    public Result viewAllMessagesByChatRoomId(Integer chatRoomId){

        if (chatRoomId == null) return Result.error("传入的聊天室的id为null");

        List<Message> messages = messageService.selectMessagesByChatRoomId(chatRoomId);

        if (messages == null) return Result.error("信息为null");

        if (messages.isEmpty()) return Result.success("信息为空，", messages);

        return Result.success("找到信息了", messages);

    }
    @RequestMapping("/driver/viewAppraise")
    public Result viewAppraise(@RequestBody String orderId) {

//        判断传入的参数是否正确
        if (orderId == null) return Result.error("查询评价传入的订单的编号为null");

        Appraise appraise = orderService.searchAppraiseByOrderId(orderId);

        if (appraise != null) return Result.success("查询订单评价成功", appraise);

        else return Result.success("查询订单评价失败", null);

    }

    // 对订单进行评价
    @RequestMapping("/driver/commentOrder")
    public Result commentOrder(@RequestBody Appraise appraise) {

        Integer count = orderService.addAppraiseIdToOrder(appraise);

        return Result.success("成功", count);

    }
    // 确认订单
    @RequestMapping("/driver/confirmOrder")
    public Result confirmOrder(@RequestBody String orderId) {

        Integer count = orderService.modOrderState(orderId, OrderState.FINSHED);

        if (count > 0) {

            return Result.success("订单支付成功");


        } else return Result.error("买票失败，没有票了。");

    }
    //    添加司机到聊天
    @RequestMapping("/driver/chatAndPassenger")
    public Result chatAndPassenger(Integer passengerId, Integer driverId) {

//        先判断这个司机是否已经在聊天表里了。如果是就不用添加了。（就是判断乘客和司机是否已经有聊天室了。）
        ChatRoom chatRoom = chatRoomService.searchChatRoomByPassengerIdAndDriverId(passengerId, driverId);

        if (chatRoom == null) {
//            没有聊天室就创建一个聊天室再返回。
            Passenger passenger = passengerService.search(passengerId);
            Driver driver = driverSerivce.search(driverId);

            return Result.success("创建聊天室", chatRoomService.createChatRoom(passenger, driver));


        } else return Result.success("已经有聊天室了", chatRoom);


    }
    //
//    将信息保存到数据库
    @RequestMapping("/driver/submitMessage")
    public Result submitMessage(@RequestBody Message message) {

        if (message == null) return Result.error("失败，传入的信息为空");

        Integer count = messageService.createMessage(message);

//        根据返回的count，如果为0就说明失败了。大于0才是成功的。
        if (count > 0) return Result.success("插入信息成功");

        else return Result.error("失败");


    }


}
