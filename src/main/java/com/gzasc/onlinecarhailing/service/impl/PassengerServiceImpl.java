package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.pojo.Ticket;
import com.gzasc.onlinecarhailing.service.PassengerService;
import com.gzasc.onlinecarhailing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }


}
