package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Ticket;

import java.util.List;

public interface TicketService {

//    增加
    public void addTicket();
//    删除
    public void removeTicket();
//    更新
    public void modTicket();
//    查询所有
    public List<Ticket> searchAllTicket();

}
