package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.Ticket;
import com.gzasc.onlinecarhailing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//对票的管理

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;


    @Override
    @Transactional
    public Integer addTicket(Ticket ticket) {
        ticketMapper.insertTicket(ticket);
        return ticket.getId();
    }

    @Override
    @Transactional
    public Integer removeTicket(Integer ticketId) {

       return ticketMapper.deleteTicket(ticketId);

    }

    @Override
    @Transactional
    public Integer modTicket(Ticket ticket) {

        return ticketMapper.updateTicket(ticket);

    }

    @Override
    public List<Ticket> searchAllTicket() {
        return ticketMapper.selectAll();
    }

    @Override
    public Ticket searchTicketById(Integer id) {

        return ticketMapper.selectTicketById(id);
    }

    @Override
    public List<Ticket> searchAvailableTicket(){
        return ticketMapper.selectAvailableTickets();
    }

    @Override
    public Integer removeTickets(List<Integer> ids) {

        return ticketMapper.deleteTicketByIds(ids);
    }
}
