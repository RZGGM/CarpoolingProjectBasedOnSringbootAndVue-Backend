package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Ticket;

import java.util.List;

public interface TicketService {

//    增加
    public void addTicket(Ticket ticket);
//    删除
    public void removeTicket(Integer ticketId);
//    更新
    public void modTicket(Ticket ticket);
//    查询所有
    public List<Ticket> searchAllTicket();
//查询单张票
    public Ticket searchTicketById();
}
