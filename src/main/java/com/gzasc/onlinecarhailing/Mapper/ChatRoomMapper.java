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
     ChatRoom searchChatRoom(Integer passengerId, Integer driverId);

     //    判断双方有无聊天室，根据管理员的id和乘客的id
     ChatRoom searchChatRoomBypassengerIdAndManagerId(Integer passengerId, Integer managerId);
     //    判断双方有无聊天室，根据管理员的id和司机的id
     ChatRoom searchChatRoomByDriverIdAndManagerId(Integer driverId, Integer managerId);

     //    判断双方有无聊天室，根据管理员的id和司机的id还有乘客的id，只要有其中两个或是一个不为null就行。都为null会返回所有的聊天室。
     ChatRoom searchChatRoomByDriverIdAndManagerIdAndPassengerId(Integer driverId,Integer passengerId, Integer managerId);

//    根据乘客的id返回它的聊天室
     List<ChatRoom> selectChatRoomsByPassengerId(Integer passengerId);

     //    根据司机的的id返回它的聊天室
     List<ChatRoom> selectChatRoomsByDriverId(Integer driverId);
// 根据管理员的id返回 聊天室
     List<ChatRoom> selectChatRoomsManagerId(Integer managerId);


//     根据聊天室的id查找聊天室
     ChatRoom selectByChatRoomId(Integer chatRoomId);

// 根据一个或多个id查找聊天室
     ChatRoom searchChatRoomByDriverIdOrManagerIdOrPassengerId(
             Integer driverId, Integer passengerId, Integer managerId);


}
