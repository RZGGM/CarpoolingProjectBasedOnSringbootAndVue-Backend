package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对订单数据库的操作
@Mapper
public interface OrderMapper {


    //     购票（核心功能），就是增加订单给自己
    Integer insertOrderById(Order order);
// 删除订单一条订单，
    Integer deleteOrderById(Integer id);
//    修改订单的状态
    Integer updateOrder(Integer orderId, Integer state);
    //    评价，应该是用户看到自己对订单的评价，司机有自己的评价，模仿闲鱼的。先对司机的表进行设计吧。
//    更新订单的评价
    Integer updateOrderAppraise(Integer orderId, Integer appraiseId);
//    查询自己的所有的订单
    List<Order> selectBySelfId(Integer id);


}
