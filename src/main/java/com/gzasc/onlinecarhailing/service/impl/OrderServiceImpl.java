package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.AppraiseMapper;
import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.Appraise;
import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.Ticket;
import com.gzasc.onlinecarhailing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//        判断是否还有票
        if (Objects.equals(ticket.getPassengerCount(), ticket.getSoldCount())) return 0;
        else return orderMapper.insertOrderById(order);
    }

    @Override
    public Integer deleteOrderById(Integer id) {


        return orderMapper.deleteOrderById(id);
    }

    @Override
    public Integer modOrderState(Integer orderId, Integer state) {


        return orderMapper.updateOrder(orderId, state);

    }

    @Override
    public List<Order> selectBySelfId(Integer id) {
        return orderMapper.selectBySelfId(id);
    }


    @Override
    public Integer addAppraiseIdToOrder(Appraise appraise) {

//        插入评价
        appraiseMapper.insertAppraise(appraise);

        return orderMapper.updateOrderAppraise(appraise.getOrderId(), appraise.getId());
    }
}