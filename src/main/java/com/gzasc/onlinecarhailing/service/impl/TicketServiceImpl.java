package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.Ticket;
import com.gzasc.onlinecarhailing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//对票的管理
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;

//    增加
    @Override
    public void addTicket(){

        ticketMapper.insertTicket();

    }

    @Override
    public void removeTicket() {

    }

    @Override
    public void modTicket() {

    }

    @Override
    public List<Ticket> searchAllTicket() {



        return null;
    }

}
