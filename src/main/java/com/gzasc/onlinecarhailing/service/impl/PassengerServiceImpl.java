package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.AccountMapper;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.pojo.Ticket;
import com.gzasc.onlinecarhailing.service.PassengerService;
import com.gzasc.onlinecarhailing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AccountMapper accountMapper;


    @Override
    public Integer register(Passenger passenger) {

        return passengerMapper.insert(passenger);
    }

    @Override
    public Integer remove(Integer id) {
        return passengerMapper.deleteById(id);
    }

    @Override
    public Integer removeByIds(List<Integer> ids) {
//        先清除乘客对应的订单，和发起的拼单
        Passenger passenger;
        List<Order> orders ;
        List<Integer> orderIds = new ArrayList<>();
        List<Integer> accountIds = new ArrayList<>() ;
        for (Integer passengerId : ids) {

            passenger = passengerMapper.selectById(passengerId);

            if (null == passenger) return 0;
//            因为乘客和订单的关系是一对多的关系，所以只需要在订单表里查询订单记录的乘客的id就行。
//  乘客类里记录订单的信息只是方便我做前端。
            orders = orderMapper.selectBySelfId(passengerId);

            accountIds.add(passenger.getAccountId());

            if (null != orders && !orders.isEmpty()) {

                for (Order order : orders) {
                    orderIds.add(order.getId());
                }
//                删除乘客对应的订单
                orderMapper.deleteOrdersByIds(orderIds);

            }
//            从帐号表中对绑定的乘客的身份进行除名。
            accountMapper.updateAccountsPassengerIdToNullByAccountIds(accountIds);
//            这里应该还有当，帐号对应的乘客和司机身份都删除时，帐号也自动清空？
//            还是说可以再次注册身份？

        }

//  最后删除乘客的信息。
        return passengerMapper.deleteByIds(ids);
    }

    @Override
    public Integer mod(Passenger newPassenger) {
        return passengerMapper.update(newPassenger);
    }

    @Override
    public Passenger search(Integer id) {
        return passengerMapper.selectById(id);
    }

    @Override
    public List<Passenger> searchAll() {



        return passengerMapper.selectAll();
    }


}
