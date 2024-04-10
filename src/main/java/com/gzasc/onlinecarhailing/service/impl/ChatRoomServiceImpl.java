package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.ChatRoomMapper;
import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRoomMapper chatRoomMapper;


    //     乘客查看自己的聊天室，传入的参数为乘客id。
    @Override
    public List<ChatRoom> searchChatRoomsByPassengerId(Integer passengerId){


        return chatRoomMapper.selectChatRoomsByPassengerId( passengerId);

    }
    //     根据乘客和司机的id查看聊天室
    @Override
    public ChatRoom searchChatRoomByPassengerIdAndDriverId(Integer passengerId, Integer driverId){


        return chatRoomMapper.searchChatRoom(passengerId, driverId);


    }

//    创建一个聊天室
    @Override
    public ChatRoom createChatRoom(Passenger self, Driver other) {

//        先判断有无聊天室的存在
        ChatRoom chatRoom = searchChatRoom(self, other);

//        再看看要是否创建一个。

//        需要创建一个
        if(chatRoom == null) {

            ChatRoom chatRoom1 = new ChatRoom();

            chatRoom1.setSelfId(self.getPassengerId());
            chatRoom1.setOtherId(other.getDriverId());

            chatRoom1.setId(chatRoomMapper.insertChatRoom(chatRoom1));


            return chatRoom1;
        }
//        不需要创建一个
        else return chatRoom;

    }


//    查找聊天室
    @Override
    public ChatRoom searchChatRoom(Passenger passenger, Driver driver) {

        return chatRoomMapper.searchChatRoom(passenger.getPassengerId(), driver.getDriverId());
    }
// 根据聊天室的id查找它对应的信息



}
