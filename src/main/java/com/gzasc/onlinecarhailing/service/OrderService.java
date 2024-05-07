package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Appraise;
import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.OrderQuery;

import java.util.List;

public interface OrderService {


    //     购票，增加订单
    Integer addTicketToPassenger(Order order);
// 拼单，增加订单给乘客
    Integer addJoinOrderToPassenger(Order order);
// 拼单，增加订单给司机
    Integer addJoinOrderToDriver(Order order);

    //    删除订单
    Integer deleteOrderById(Integer id);

//    更改订单的状态
    Integer modOrderState(String orderId, Integer state);

    //    乘客查看自己的所有订单
    List<Order> searchBySelfId(Integer id);

//    乘客对订单进行评价
    Integer addAppraiseIdToOrder(Appraise appraise);


//    通过订单的编号进行取消订单
    Integer modOrderByOrderId(String orderId);

//    根据订单的状态查询订单
    List<Order> searchOrdersByOrderState(Integer state);
//    返回非自己帐号创建的拼车订单，司机的
    List<Order> searchOrdersByAccount(Account account);



//    根据订单的类型来查询订单
    List<Order> searchOrdersByOrderType(Integer type);



//     根据订单的编号查询订单的评价
    Appraise searchAppraiseByOrderId(String orderId);

//
//    根据订单的所有者的身份和对应身份的id来获取订单
    List<Order> searchOrdersByCreateUserTypeAndOwnerId(Integer createUserType, Integer ownerId);
// 加上个页码用和缓存区分。但是更新缓存时怎么办？
//    将参数更改为整个前端传来的参数，size，计算出总页码，再拼接得到key，全部移去。
    List<Order> searchOrdersByCreateUserTypeAndOwnerId(Integer createUserType, Integer ownerId, OrderQuery orderQuery);

}
