package com.gzasc.onlinecarhailing.controller;


import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.pojo.Ticket;
import com.gzasc.onlinecarhailing.service.AccountService;
import com.gzasc.onlinecarhailing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//帐号接口的定义，不过对帐号的接口不应该定义在这
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    TicketService ticketService;


//    管理员后台修改帐号信息，如改密码什么的。


@RequestMapping("/system/editAccount")
    public Result editAccount(Account account){

        Integer count = accountService.mod(account);

        if (0 != count) return Result.success("修改成功", count);

        return Result.error("修改失败");

    }

//    管理员后台，删除帐号
@RequestMapping("/system/deleteAccountsById")
    public Result deleteAccountsById(List<Integer> ids){


        Integer countAccount = accountService.removeAccountsByIds(ids);
        if (countAccount > 0) return Result.success("成功");
        else return Result.error("删除失败");

    }
//增加一张车票
@RequestMapping("/system/addTicket")
    public Result addTicket(Ticket ticket){



        Ticket ticket1 = ticketService.searchTicketById(ticketService.addTicket(ticket));

        if (null != ticket1) {
            return Result.success("添加成功", ticket1);
        } else return Result.error("添加失败");

    }
//    删除车票
    public Result removeTicketById(Integer id){

        Integer count = ticketService.removeTicket(id);

        if (count > 0){
            return Result.success("删除成功",count);
        }else return Result.error("删除失败");

    }
//    批量删除车票
    public Result removeTicketsByIds(List<Integer> ids){

        Integer count = ticketService.removeTickets(ids);

        if (count > 0){
            return Result.success("批量删除成功", ids);
        }else return Result.error("失败");

    }

//    修改车票信息
    public Result editTicket(Ticket ticket){

        Integer count = ticketService.modTicket(ticket);

        ticket = ticketService.searchTicketById(ticket.getId());

        if (count > 0){
            return Result.success("批量删除成功", ticket);
        }else return Result.error("失败");
    }


}
