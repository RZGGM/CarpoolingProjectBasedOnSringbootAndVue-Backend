package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;

//对票（或者说是司机发出的订单的管理）
@Mapper
public interface TicketMapper {

//    增加
    public void insertTicket();
// 删除
    public void deleteTicket();
//    修改
    public void updateTicket();
//    查询所有
    public Ticket selectAll();
//    查询一条，根据ID查询
    public Ticket selectTicketById();
}
