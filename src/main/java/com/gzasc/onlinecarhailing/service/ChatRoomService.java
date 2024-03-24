package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;

//聊天室服务层
public interface ChatRoomService {

//    创建一个聊天室
     ChatRoom createChatRoom(Passenger self, Driver other);

//    查打聊天室
     ChatRoom searchChatRoom(Passenger self, Driver other);


}
