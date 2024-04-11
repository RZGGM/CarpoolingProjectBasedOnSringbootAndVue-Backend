package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;

import java.util.List;

//聊天室服务层
public interface ChatRoomService {

    //    创建一个聊天室
    ChatRoom createChatRoom(Passenger self, Driver other);


    //    查打聊天室
    ChatRoom searchChatRoom(Passenger self, Driver other);

    //     根据乘客和司机的id查看聊天室
    ChatRoom searchChatRoomByPassengerIdAndDriverId(Integer passengerId, Integer driverId);

    //     乘客查看自己的聊天室，传入的参数为乘客id。
    List<ChatRoom> searchChatRoomsByPassengerId(Integer passengerId);

    //     根据司机的id查找他的聊天室
    List<ChatRoom> searchChatRoomsByDriverId(Integer driverId);


}
