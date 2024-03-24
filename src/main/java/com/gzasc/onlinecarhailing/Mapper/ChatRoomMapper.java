package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

//操作聊天数据库的
@Mapper
public interface ChatRoomMapper {

//    创建一个聊天室。
     Integer insertChatRoom(ChatRoom chatRoom);
//    修改聊天室
     void updateChatRoom(ChatRoom chatRoom);

//    判断双方有无聊天室
     ChatRoom searchChatRoom(Integer selfId, Integer otherId);


}
