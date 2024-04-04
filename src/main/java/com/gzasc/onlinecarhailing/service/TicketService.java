package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Ticket;

import java.util.List;

public interface TicketService {

//    增加
     Integer addTicket(Ticket ticket);
//    删除
     Integer removeTicket(Integer ticketId);
//    更新
     Integer modTicket(Ticket ticket);
//    查询所有
     List<Ticket> searchAllTicket();
//查询单张票
     Ticket searchTicketById(Integer id);

//     查询状态为可以购买的票
     List<Ticket> searchAvailableTicket();

//     批量删除车票
     Integer removeTickets(List<Integer> ids);

}
