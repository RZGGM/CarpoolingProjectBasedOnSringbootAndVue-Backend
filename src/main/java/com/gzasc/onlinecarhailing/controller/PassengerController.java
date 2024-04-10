package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.*;
import com.gzasc.onlinecarhailing.utils.OrderUtils;
import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

//乘客的接口

@RestController
public class PassengerController {

    @Autowired
    MessageService messageService;

    @Autowired
    PassengerService passengerService;
    @Autowired
    OrderService orderService;
    @Autowired
    DriverSerivce driverSerivce;
    @Autowired
    ChatRoomService chatRoomService;

    // 修改个人信息
    @RequestMapping("/passenger/modPersonalInfo")
    public Result alterUserData(Passenger passenger) {

        if (passengerService.mod(passenger) > 0) {
//            修改成功
            return Result.success("修改成功");

        } else return Result.error("修改失败");

    }

    //    查看个人信息
    @RequestMapping("/passenger/checkPersonalInfomation")
    public Result searchById(Integer id) {

        Passenger passenger = passengerService.search(id);

        if (null != passenger) {

            return Result.success("查看成功", passenger);

        } else return Result.error("查看失败");

    }

    //    发出一个拼单订单
    @RequestMapping("/passenger/createShareTheBill")
    public Result createShareTheBill(@RequestBody Order order) {

        if (null == order) return Result.error("订单为null");

        if (null == order.getOrderType()) return Result.error("订单类型为null");

//        判断订单是不是拼单类型的订单，如果不是就return。
        if (JoinOrderCanJoin.NON_JOIN_ORDER.equals(order.getJoinOrderCanJoin())) return Result.error("并非是拼单订单。");

//        判断订单类型。
        if (OrderType.PASSENGER_CREATE_ORDER.equals(order.getOrderType())) {
//如果是乘客发起的订单就进来
            Integer count = orderService.addJoinOrderToPassenger(order);

            if (count > 0) return Result.success("创建订单成功");

            else return Result.error("创建订单失败");
        }
        return Result.error("创建订单失败，因为类型不符合。");
    }


    //    购票
    @RequestMapping("/passenger/buyTicket")
    public Result buyTicket(@RequestBody Order order) {

//         设置订单的编号
        order.setOrderId(OrderUtils.createOrderCode());

        if (order.getPassengerId() == null || order.getTicketId() == null)
            return Result.error("购票失败了，传入的信息为空。");


        Integer count = orderService.addTicketToPassenger(order);


        if (count > 0) {

            return Result.success("买票成功");


        } else return Result.error("买票失败，没有票了。");


    }

    //    支付订单
    @RequestMapping("/passenger/payOrder")
    public Result payOrder(@RequestBody String orderId) {

        Integer count = orderService.modOrderState(orderId, OrderState.WAIT_CONFIRM);

        if (count > 0) {

            return Result.success("订单支付成功");


        } else return Result.error("买票失败，没有票了。");

    }


    // 确认订单
    @RequestMapping("/passenger/confirmOrder")
    public Result confirmOrder(@RequestBody String orderId) {

        Integer count = orderService.modOrderState(orderId, OrderState.FINSHED);

        if (count > 0) {

            return Result.success("订单支付成功");


        } else return Result.error("买票失败，没有票了。");

    }


    //    取消自己的订单
    @RequestMapping("/passenger/cancelOrder")
    public Result cancelOrder(@RequestBody String orderId) {


        Integer count = orderService.abolishOrderByOrderId(orderId);

        if (count > 0) return Result.success("乘客取消订单成功");
        else return Result.error("乘客取消订单失败");

    }

    //    查看自己的所有订单
    @RequestMapping("/passenger/allOrder")
    public Result viewAllOrder(Integer createUserType, Integer passengerId) {

        List<Order> orders = orderService.searchOrdersByCreateUserTypeAndOwnerId(createUserType, passengerId);


        return Result.success("查看成功", orders);


    }
//    查看订单详细,应该是前端的


    //    删除订单
    @RequestMapping("/passenger/deleteOrder")
    public Result removeOrderByOrderId(Integer id) {

        Integer count = orderService.deleteOrderById(id);

        if (count > 0) return Result.success("删除订单成功");
        else return Result.error("删除失败");

    }


    // 对订单进行评价
    @RequestMapping("/passenger/commentOrder")
    public Result commentOrder(@RequestBody Appraise appraise) {

        Integer count = orderService.addAppraiseIdToOrder(appraise);

        return Result.success("成功", count);

    }

    //    乘客接受司机的订单，应该设计和购票一样，可以自己选择自己这一订单的同行人数。
//     所以传入的参数应该有两个，一个是司机的基本拼车的订单，另一个是自己填写的拼车订单。
//     要自己填写的拼单信息是为了自己的同行人数，和乘客的id，和价格。
    @RequestMapping("/passenger/acceptOrder")
    public Result acceptOrder(@RequestBody HashMap<String, Order> orderHashMap) {

//    前端传的数组类型的数据，不知道为什么会变成HashMap类型的，所以只能处理成List类型的了。
        Order driverCreateJoinOrder = orderHashMap.get("driverCreateJoinOrder");
        Order passengerOrder = orderHashMap.get("passengerOrder");

        if (driverCreateJoinOrder != null && passengerOrder != null) {

            if (driverCreateJoinOrder.getDriverId() == null || passengerOrder.getPassengerCount() == null) {
                return Result.error("传入的订单有Null");
            }

//        根据订单来生成订单。
            Integer count = passengerService.addOrderOnJoinOrderToPassenger(driverCreateJoinOrder, passengerOrder);

            if (Objects.equals(count, OrderState.PASSENGER_COUNT_MAX)) return Result.error("失败，空位不足。");

            else return Result.success("拼车成功", count);
        }
        return Result.error("错误，拼车的其中有个订单为null");
    }

    @RequestMapping("/passenger/viewCanJoinOrder")
    public Result viewCanJoinOrder() {
//  查看所有可以拼单的订单
//        根据状态查询司机发起的可以拼车的订单
        List<Order> orders = orderService.searchOrdersByOrderState(OrderState.DRIVER_CREATE_SHARE_BILL_WAIT_PASSENGER);

        if (!orders.isEmpty()) return Result.success("乘客查询可以拼车的订单成功", orders);
        else return Result.success("没有可以拼单的订单");

    }

    @RequestMapping("/passenger/viewAppraise")
    public Result viewAppraise(@RequestBody String orderId) {

//        判断传入的参数是否正确
        if (orderId == null) return Result.error("查询评价传入的订单的编号为null");

        Appraise appraise = orderService.searchAppraiseByOrderId(orderId);

        if (appraise != null) return Result.success("查询订单评价成功", appraise);

        else return Result.success("查询订单评价失败", null);

    }

    //    添加司机到聊天
    @RequestMapping("/passenger/chatAndDriver")
    public Result chatAndDriver(Integer passengerId, Integer driverId) {

//        先判断这个司机是否已经在聊天表里了。如果是就不用添加了。（就是判断乘客和司机是否已经有聊天室了。）
        ChatRoom chatRoom = chatRoomService.searchChatRoomByPassengerIdAndDriverId(passengerId, driverId);


        if (chatRoom == null) {
//            没有聊天室就创建一个聊天室再返回。
            Passenger passenger = passengerService.search(passengerId);
            Driver driver = driverSerivce.search(driverId);


            return Result.success("创建聊天室", chatRoomService.createChatRoom(passenger, driver));


        } else return Result.success("已经有聊天室了", chatRoom);


    }


//    查看乘客自己的所有的聊天室
    @RequestMapping("/passenger/viewAllChatRooms")
    public Result viewAllChatRooms(Integer passengerId){

        List<ChatRoom> chatRooms = chatRoomService.searchChatRoomsByPassengerId(passengerId);

        if (chatRooms == null) return Result.error("没有聊天室");

        if (chatRooms.isEmpty()) return Result.error("聊天室的列表为空，没有找到聊天室");

        else return Result.success("这就是所有的聊天室了。", chatRooms);


    }
//    根据传入的聊天室的id找信息
    @RequestMapping("/passenger/viewAllMessagesByChatRoomId")
    public Result viewAllMessagesByChatRoomId(Integer chatRoomId){

        if (chatRoomId == null) return Result.error("传入的聊天室的id为null");

        List<Message> messages = messageService.selectMessagesByChatRoomId(chatRoomId);

        if (messages == null) return Result.error("信息为null");

        if (messages.isEmpty()) return Result.success("信息为空，", messages);

        return Result.success("找到信息了", messages);

    }
//    将信息保存到数据库
    @RequestMapping("/passenger/submitMessage")
    public Result submitMessage(@RequestBody Message message){

        if (message == null) return Result.error("失败，传入的信息为空");

        Integer count = messageService.createMessage(message);

//        根据返回的count，如果为0就说明失败了。大于0才是成功的。
        if (count > 0) return Result.success("插入信息成功");

        else return Result.error("失败");


    }



}
