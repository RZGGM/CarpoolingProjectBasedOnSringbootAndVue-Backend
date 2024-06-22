package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;

import java.util.List;

//聊天室服务层
public interface ChatRoomService {

    //    创建一个聊天室，根据传入的chatroom
    ChatRoom createChatRoom(ChatRoom chatRoom);

    //    创建一个聊天室，乘客和司机
    ChatRoom createChatRoom(Passenger self, Driver other);

    //    创建一个聊天室，乘客和管理员
    ChatRoom createChatRoom(Passenger self, Integer managerId);

    //    司机和管理员
    ChatRoom createChatRoom(Driver self, Integer managerId);

    //    查打聊天室
    ChatRoom searchChatRoom(Passenger self, Driver other);

    //     根据乘客和司机的id查看聊天室
    ChatRoom searchChatRoomByPassengerIdAndDriverId(Integer passengerId, Integer driverId);

    //     乘客查看自己的聊天室，传入的参数为乘客id。
    List<ChatRoom> searchChatRoomsByPassengerId(Integer passengerId);

    //     根据司机的id查找他的聊天室
    List<ChatRoom> searchChatRoomsByDriverId(Integer driverId);

    //    查看管理员的聊天室
    List<ChatRoom> searchChatRoomsByManagerId(Integer managerId);

    //    根据管理员的id和乘客的id查找聊天室。
    ChatRoom searchChatRoom(Passenger passenger, Integer managerId);

    //    查找聊天室，司机和管理员

    ChatRoom searchChatRoom(Driver driver, Integer managerId);

    //查看聊天室，司机，乘客，管理员
    ChatRoom searchChatRoom(Driver driver, Passenger passenger, Integer managerId);

    //    查看聊天室的,需要三个id就行
    ChatRoom searchChatRoom(Integer driverId, Integer passengerId, Integer managerId);

}
