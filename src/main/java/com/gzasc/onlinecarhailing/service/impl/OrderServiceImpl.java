package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.AppraiseMapper;
import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//订单管理
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    AppraiseMapper appraiseMapper;
    @Autowired
    DriverMapper driverMapper;

    //    创建订单,乘客购买车票创建的订单在乘客层
    @Override
    public Integer addTicketToPassenger(Order order) {

        Integer ticketId = order.getTicketId();
        Ticket ticket = ticketMapper.selectTicketById(ticketId);
// 设置这个订单的状态为等待支付。
        order.setState(-5);

//        判断是否还有票
        if (Objects.equals(ticket.getPassengerCount(), ticket.getSoldCount())) return 0;
        else {

            ticket.setSoldCount(ticket.getSoldCount() + 1);
//            要先将票的数量减1
            ticketMapper.updateTicket(ticket);

            return orderMapper.insertOrderById(order);

        }
    }

    @Override
    public Integer deleteOrderById(Integer id) {


        return orderMapper.deleteOrderById(id);
    }

    @Override
    public Integer modOrderState(String orderId, Integer state) {


//        当状态为确认订单完成时，要设置订单的完成时间
        if (OrderState.FINSHED.equals(state)) {

            orderMapper.updateOrderFinishTime(orderId);

        }


        return orderMapper.updateOrder(orderId, state);

    }

    @Override
    public List<Order> selectBySelfId(Integer id) {
        return orderMapper.selectBySelfId(id);
    }


    @Override
    public Integer addAppraiseIdToOrder(Appraise appraise) {

//        返回一个数字，0是表示评价不成功，1是评价成功
        Integer count = 0;

//        先判断订单是否的状态是否可以评价了。
        Order order = orderMapper.selectByOrderId(appraise.getOrderId());
//        判断订单的状态
        if (!order.getState().equals(OrderState.APPRAISED)) {
//        当状态为没有评价时，进入到评价。
//            插入评价
            appraiseMapper.insertAppraise(appraise);
//            更新订单的状态
            orderMapper.updateOrder(order.getOrderId(), OrderState.APPRAISED);
//             更新对应的订单的评论的id。
            count = orderMapper.updateOrderAppraise(appraise.getOrderId(), appraise.getId());

            return count;

        } else return count;

    }

    @Override
    public Integer abolishOrderByOrderId(String orderId) {

//        先查询这个订单的信息
        Order order = orderMapper.selectByOrderId(orderId);

//        判断是否找到对应的订单，找到就进入到下一步。
        if (order == null) return 0;
//        判断是哪一方发起的。
        if (UserType.PASSENGER.equals(order.getCreateUserType())) {
//            乘客发起的进入到这里。
//            再查询是否有司机接取了，有的话，要设置对应的司机的订单状态为乘客已经取消
//            -1表示为官方的订单和没有司机接取的订单
            if (order.getDriverId().equals(-1)) {
//                没有的司机接收的话，设置为已经取消

//                在取消前，是官方的票的话，要将它售出的数量-1。
                Ticket ticket = ticketMapper.selectTicketById(order.getTicketId());
                if (null != ticket) {
                    ticket.setSoldCount(ticket.getSoldCount() - 1);
                    ticketMapper.updateTicket(ticket);
                }

                return orderMapper.updateOrder(orderId, OrderState.CONCELED);
            } else {
//                设置为司机的订单为，乘客已经取消
                return orderMapper.updateOrder(order.getOtherId(), OrderState.PASSENGER_CONCEL);
            }
        } else if (UserType.DRIVER.equals(order.getCreateUserType())) {
//            司机发起的，进入到这里
//            判断有没乘客已经接取的
            if (order.getPassengerId().equals(-1)) {
                return orderMapper.updateOrder(orderId, OrderState.DRIVER_CONCEL);
            } else {
//                设置乘客的订单为，司机已经取消
                return orderMapper.updateOrder(order.getOtherId(), OrderState.DRIVER_CONCEL);
            }
        } else return 0;
    }

    @Override
    public List<Order> searchOrdersByOrderState(Integer state) {


        return null;
    }

    @Override
    public List<Order> searchOrdersByOrderType(Integer orderType) {

//        判断类型，来看是返回哪些订单
        if (Objects.equals(orderType, OrderType.PASSENGER_WAIT_DRIVER)) {
//            返回司机可以看到的，送乘客发起的。
            return orderMapper.selectByOrderType(orderType);
        } else if (orderType.equals(OrderType.PASSENGER_CAN_JOIN)) {
//            返回乘客可以看到的拼单，并且是自己可以拼单的。
            List<Order> orders = new ArrayList<>();
            List<Order> ordersDriverCreate =
                    orderMapper.selectByOrderType(OrderType.DRIVER_WAIT_PASSENGER);
            List<Order> ordersDriverAndPassengerWait =
                    orderMapper.selectByOrderType(OrderType.DRIVER_AND_PASSENGER_WAIT_OTHERPASSENGER);
            List<Order> ordersPassengerAndDriverWait =
                    orderMapper.selectByOrderType(OrderType.PASSENGER_AND_DRIVER_WAIT_OTHERPASSENGER);
            orders.addAll(ordersDriverCreate);
            orders.addAll(ordersDriverAndPassengerWait);
            orders.addAll(ordersPassengerAndDriverWait);
            return orders;
        } else return orderMapper.selectByOrderType(orderType);


    }

    @Override
    public Appraise searchAppraiseByOrderId(String orderId) {

        return appraiseMapper.selectAppraiseByOrderId(orderId);

    }
}