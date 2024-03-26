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
//    查询自己的所有的订单
    List<Order> selectBySelfId(Integer id);


}
