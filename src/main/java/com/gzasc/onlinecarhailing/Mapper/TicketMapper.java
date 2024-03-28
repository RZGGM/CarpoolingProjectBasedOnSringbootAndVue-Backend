package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对票（或者说是司机发出的订单的管理）
@Mapper
public interface TicketMapper {

    //    增加
    public Integer insertTicket(Ticket ticket);

    // 删除
    public Integer deleteTicket(Integer id);

    //    修改
    public Integer updateTicket(Ticket ticket);

    //    查询所有
    public List<Ticket> selectAll();

    //    查询一条，根据ID查询
    public Ticket selectTicketById(Integer id);

    //批量删除车票
    Integer deleteTicketByIds(List<Integer> ids);

}
