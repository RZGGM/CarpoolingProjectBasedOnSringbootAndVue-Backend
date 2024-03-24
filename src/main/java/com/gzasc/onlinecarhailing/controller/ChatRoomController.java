package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRoomController {

    @Autowired
    ChatRoomService chatRoomService;


//    插入或重新进行一个聊天室
    @RequestMapping("/chating")
    public Result buildChatRoom(Chaters chaters){

        Passenger passenger = chaters.getPassenger();
        Driver driver = chaters.getDriver();

//      得到聊天室，如果聊天室的id为null，就说明没有。此时才真正的插入。就返回原来的聊天室。
//        会在service层判断。

        ChatRoom chatRoom1 = chatRoomService.createChatRoom(passenger, driver);

        if(chatRoom1.getId() != null){

            chatRoom1.setName(passenger.getName());

            return Result.success("进行聊天", chatRoom1);

        }else return Result.error("失败");



    }

}
