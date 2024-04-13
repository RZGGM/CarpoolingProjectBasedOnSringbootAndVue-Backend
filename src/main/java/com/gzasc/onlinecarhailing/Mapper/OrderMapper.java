package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对订单数据库的操作
@Mapper
public interface OrderMapper {

    //     购票（核心功能），就是增加订单给自己
    Integer insertOrder(Order order);
// 删除订单一条订单，
    Integer deleteOrderById(Integer id);

//    根据传入的很多的订单的id来删除订单
    Integer deleteOrdersByIds(List<Integer> ids);
//    修改订单的状态
    Integer updateOrder(String orderId, Integer state);
//    修改订单的司机或是乘客或是其它，
    Integer updateOrderDriverOrPassenger(Order order);

//     更改订单的完成时间
    Integer updateOrderFinishTime(String orderId);

    //    评价，应该是用户看到自己对订单的评价，司机有自己的评价，模仿闲鱼的。先对司机的表进行设计吧。
//    更新订单的评价
    Integer updateOrderAppraise(String orderId, Integer appraiseId);
//    查询自己的所有的订单
    List<Order> selectBySelfId(Integer userId);

// 根据订单的id返回订单
    Order selectByOrderId(String orderId);
//    根据订单的状态返回订单
    List<Order> selectByOrderState(Integer state);
//    根据订单的类型来返回订单
    List<Order> selectByOrderType(Integer orderType);

//    根据订单的所有者的身份和对应身份的id来获取订单
    List<Order> selectByCreateUserTypeAndOwnerId(Integer createUserType, Integer ownerId);


}
