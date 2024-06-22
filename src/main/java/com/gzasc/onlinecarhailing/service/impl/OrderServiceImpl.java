package com.gzasc.onlinecarhailing.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.gzasc.onlinecarhailing.Mapper.AppraiseMapper;
import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.OrderService;
import com.gzasc.onlinecarhailing.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//订单管理

@CacheConfig(cacheNames = "order") //使用这个注解来管理这个类中使用其它如cacheable这些注解里的属性
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

    //    创建订单,乘客购买车票创建的订单
    @Override
    @Transactional
    public Integer addTicketToPassenger(Order order) {

//       通过订单获得票的id。因为还要对票进行操作。
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
// 设置乘客订单的手机号
            order.setPhone(RandomUtil.randomNumbers(11));

//            为乘客插入订单
            return orderMapper.insertOrder(order);

        }
    }

    // 拼单订单
    @Override
    @Transactional
    public Integer addJoinOrderToPassenger(Order order) {
//            将订单类型改为让司机可以看到
        order.setOrderType(OrderType.PASSENGER_WAIT_DRIVER);
//        修改订单的状态为等待司机
        order.setState(OrderState.PASSENGER_CREATE_SHARE_BILL_WAIT_DRIVER);

//        生成订单编号
        order.setOrderId(OrderUtils.createOrderCode());

//        调用mapper将这个订单给放入到数据库
        return orderMapper.insertOrder(order);

    }

    @Override
    @Transactional
    public Integer addJoinOrderToDriver(Order order) {
        //            将订单类型改为司机等待乘客，这样，乘客就可以看到这个订单了。
        order.setOrderType(OrderType.DRIVER_WAIT_PASSENGER);
//        修改订单的状态为等待乘客
        order.setState(OrderState.DRIVER_CREATE_SHARE_BILL_WAIT_PASSENGER);
//        生成订单编号
        order.setOrderId(OrderUtils.createOrderCode());
//        调用mapper将这个订单给放入到数据库
        return orderMapper.insertOrder(order);


    }


    @Override
    @Transactional

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
    @Transactional

    public Integer deleteOrderById(Integer id) {


        return orderMapper.deleteOrderById(id);
    }

    @Override
    @Transactional

    public Integer modOrderState(String orderId, Integer state) {


//        当状态为确认订单完成时，要设置订单的完成时间
        if (OrderState.FINSHED.equals(state)) {

            orderMapper.updateOrderFinishTime(orderId);

        }


        return orderMapper.updateOrder(orderId, state);

    }

// 这个应该算是更新订单。
    @Override
    @Transactional

    public Integer modOrderByOrderId(String orderId) {

//        先查询这个订单的信息
        Order order = orderMapper.selectByOrderId(orderId);

//        判断是否找到对应的订单，找到就进入到下一步。
        if (order == null) return 0;
//        判断是哪一方发起的，就是这个订单是哪个用户类型拥有的。。（感觉这样写，复杂了，其实只用判断是否有otherId就行了。）
        if (UserType.PASSENGER.equals(order.getCreateUserType())) {
//            乘客发起的进入到这里。
//            再查询是否有司机接取了，有的话，要设置对应的司机的订单状态为乘客已经取消
//            -1表示为官方的订单和没有司机接取的订单
            if (order.getDriverId().equals(-1)) {
//                没有的司机接收的话，设置为已经取消
//                在取消前，是官方的票的话，要将它售出的数量-1。
                if (order.getTicketId() != null) {
                    Ticket ticket = ticketMapper.selectTicketById(order.getTicketId());

                    if (null != ticket) {
                        ticket.setSoldCount(ticket.getSoldCount() - 1);
                        ticketMapper.updateTicket(ticket);
                    }
                }

                return orderMapper.updateOrder(orderId, OrderState.CONCELED);
            } else {
//                设置为司机的订单为，乘客已经取消
                orderMapper.updateOrder(order.getOtherId(), OrderState.PASSENGER_CONCEL);
//                再设置自己的订单的状态为已经取消了。
                return orderMapper.updateOrder(orderId, OrderState.CONCELED);
            }
        } else if (UserType.DRIVER.equals(order.getCreateUserType())) {
//            司机发起的，进入到这里
//            判断有没乘客已经接取的
            if (order.getPassengerId().equals(-1)) {
//                没有乘客接单就进入到这，设置订单状态为司机已经取消了。
                return orderMapper.updateOrder(orderId, OrderState.CONCELED);
            } else {
//                已经有乘客接单的：设置乘客的订单为，司机已经取消
                orderMapper.updateOrder(order.getOtherId(), OrderState.DRIVER_CONCEL);
//                再设置自己的订单的状态为：已经取消
                return orderMapper.updateOrder(orderId, OrderState.CONCELED);
            }
        } else return 0;
    }

    @Override

    public List<Order> searchOrdersByOrderState(Integer state) {


        return orderMapper.selectByOrderState(state);
    }

    @Override

    public List<Order> searchOrdersByAccount(Account account) {

//        判断传入的帐号的类型，判断要找出什么类型的订单。
        if (account.getIt().equals(UserType.PASSENGER)) {

//            找出司机创建的，因为是乘客要接的。所以是司机创建的
            return orderMapper.selectByOrderStateAndDriverId(
                    OrderState.DRIVER_CREATE_SHARE_BILL_WAIT_PASSENGER,
                    account.getDriverId()
            );
        } else {
            return orderMapper.selectByOrderStateAndPassengerId(
                    OrderState.PASSENGER_CREATE_SHARE_BILL_WAIT_DRIVER,
                    account.getPassengerId()
            );
        }

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
                    orderMapper.selectByOrderType(OrderType.DRIVER_AND_PASSENGER_WAIT_OTHER_PASSENGER);
            List<Order> ordersPassengerAndDriverWait =
                    orderMapper.selectByOrderType(OrderType.PASSENGER_AND_DRIVER_WAIT_OTHER_PASSENGER);
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

    //    根据订单的所有者的身份和对应身份的id来获取订单，如果分页的结果和不分页的都用这个那会让键重复，改造下吧。
    @Override
    public List<Order> searchOrdersByCreateUserTypeAndOwnerId(Integer createUserType, Integer ownerId) {

        return orderMapper.selectByCreateUserTypeAndOwnerId(createUserType, ownerId);

    }
    @Override
    public List<Order> searchOrdersByCreateUserTypeAndOwnerId(Integer createUserType, Integer ownerId, OrderQuery orderQuery) {

        return orderMapper.selectByCreateUserTypeAndOwnerId(createUserType, ownerId);

    }
    @Override
    public List<Order> searchBySelfId(Integer id) {

        return orderMapper.selectBySelfId(id);
    }

}