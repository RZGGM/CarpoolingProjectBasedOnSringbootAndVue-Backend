package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//操作聊天数据库的
@Mapper
public interface ChatRoomMapper {

//    创建一个聊天室。
     Integer insertChatRoom(ChatRoom chatRoom);
//    修改聊天室
     void updateChatRoom(ChatRoom chatRoom);

//    判断双方有无聊天室
     ChatRoom searchChatRoom(Integer selfId, Integer otherId);
//    根据乘客的id返回它的聊天室
     List<ChatRoom> selectChatRoomsByPassengerId(Integer passengerId);

}
