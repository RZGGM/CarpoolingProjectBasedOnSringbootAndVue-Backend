package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Ticket;

import java.util.List;

public interface TicketService {

//    增加
     void addTicket(Ticket ticket);
//    删除
     void removeTicket(Integer ticketId);
//    更新
     void modTicket(Ticket ticket);
//    查询所有
     List<Ticket> searchAllTicket();
//查询单张票
     Ticket searchTicketById();
}
