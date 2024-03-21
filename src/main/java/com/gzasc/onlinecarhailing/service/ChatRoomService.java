package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.User;

//聊天室服务层
public interface ChatRoomService {

//    创建一个聊天室
    public ChatRoom createChatRoom(User self, User other);

//    查打聊天室
    public ChatRoom searchChatRoom(User self, User other);

}
