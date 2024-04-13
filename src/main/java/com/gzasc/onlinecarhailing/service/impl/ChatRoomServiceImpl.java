package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.ChatRoomMapper;
import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRoomMapper chatRoomMapper;
    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    DriverMapper driverMapper;


    //    创建一个聊天室，根据传入的chatroom
    @Override
    public ChatRoom createChatRoom(ChatRoom chatRoom) {

        Integer chatRoomId = chatRoomMapper.insertChatRoom(chatRoom);

        return chatRoomMapper.selectByChatRoomId(chatRoomId);

    }


    //     乘客查看自己的聊天室，传入的参数为乘客id。
    @Override
    public List<ChatRoom> searchChatRoomsByPassengerId(Integer passengerId) {

        List<ChatRoom> chatRooms = chatRoomMapper.selectChatRoomsByPassengerId(passengerId);

        List<ChatRoom> chatRooms1 = new ArrayList<>();

        for (ChatRoom chatRoom : chatRooms) {

//            设置聊天室的名字
//            看是什么类型的聊天室。通过聊天室里的人的id来判断。
            Integer driverId = chatRoom.getDriverId();
            Integer mangerId = chatRoom.getManagerId();
            Integer passengerId1 = chatRoom.getPassengerId();

//            是乘客和司机交流的聊天室
            if (driverId != null && passengerId1 != null && mangerId == null) {


                Driver driver = driverMapper.selectById(driverId);
//            设置和用户聊天的那个的名字，乘客就这样设置
                String chatRoomName = driver.getName();
                chatRoom.setName(chatRoomName);
            } else if (passengerId1 != null && mangerId != null && driverId == null) {
//              是乘客和管理员的聊天室，司机或是乘客都这样设置
                chatRoom.setName("客服" + mangerId);

            } else if (passengerId1 != null && mangerId != null && driverId != null) {

                Driver driver = driverMapper.selectById(driverId);
//            设置和用户聊天的那个的名字
                String driverName = driver.getName();

//                是三方聊天室，乘客就这样设置
                chatRoom.setName("客服" + mangerId + "，" + driverName);

            } else {

//                是司机和管理员的聊天室，司机或是乘客都这样设置

                chatRoom.setName("客服" + mangerId);

            }


            chatRooms1.add(chatRoom);
        }

        return chatRooms1;


    }

    //     司机查看自己的聊天室，传入的参数为司机id。
    @Override
    public List<ChatRoom> searchChatRoomsByDriverId(Integer driverId) {

        List<ChatRoom> chatRooms = chatRoomMapper.selectChatRoomsByDriverId(driverId);

        List<ChatRoom> chatRooms1 = new ArrayList<>();

        for (ChatRoom chatRoom : chatRooms) {


//            设置聊天室的名字
//            看是什么类型的聊天室。通过聊天室里的人的id来判断。
            Integer driverId1 = chatRoom.getDriverId();
            Integer mangerId = chatRoom.getManagerId();
            Integer passengerId = chatRoom.getPassengerId();

//            是乘客和司机交流的聊天室
            if (driverId1 != null && passengerId != null && mangerId == null) {


                Passenger passenger = passengerMapper.selectById(passengerId);
//            设置和用户聊天的那个的名字，司机就这样设置
                String chatRoomName = passenger.getName();

                chatRoom.setName(chatRoomName);
            } else if (passengerId != null && mangerId != null && driverId1 == null) {
//              是乘客和管理员的聊天室，司机或是乘客都这样设置
                chatRoom.setName("客服" + mangerId);

            } else if (passengerId != null && mangerId != null && driverId1 != null) {

                Passenger passenger = passengerMapper.selectById(driverId1);
//            设置和用户聊天的那个的名字
                String passengerName = passenger.getName();

//                是三方聊天室，司机就这样设置
                chatRoom.setName("客服" + mangerId + "，" + passengerName);

            } else {
//                是司机和管理员的聊天室，司机或是乘客都这样设置
                chatRoom.setName("客服" + mangerId);

            }
            chatRooms1.add(chatRoom);
        }

        return chatRooms1;

    }

    @Override
    public List<ChatRoom> searchChatRoomsByManagerId(Integer managerId) {

        List<ChatRoom> chatRooms = chatRoomMapper.selectChatRoomsManagerId(managerId);

        List<ChatRoom> chatRooms1 = new ArrayList<>();

        for (ChatRoom chatRoom : chatRooms) {


//            设置聊天室的名字
//            看是什么类型的聊天室。通过聊天室里的人的id来判断。
            Integer driverId1 = chatRoom.getDriverId();
            Integer mangerId = chatRoom.getManagerId();
            Integer passengerId = chatRoom.getPassengerId();

//            是乘客和司机交流的聊天室
            if (driverId1 != null && passengerId != null && mangerId == null) {


                Passenger passenger = passengerMapper.selectById(passengerId);
//            设置和用户聊天的那个的名字，司机就这样设置
                String chatRoomName = passenger.getName();

                chatRoom.setName(chatRoomName);
            } else if (passengerId != null && mangerId != null && driverId1 == null) {
//              是乘客和管理员的聊天室，管理员设置为：用户名+id。

                Passenger passenger = passengerMapper.selectById(passengerId);
//            设置和用户聊天的那个的名字，管理员就这样设置
                String chatRoomName = "乘客："+passenger.getName();

                chatRoom.setName(chatRoomName+"  "+passenger.getPassengerId());


            } else if (passengerId != null && mangerId != null && driverId1 != null) {

                Passenger passenger = passengerMapper.selectById(driverId1);
                Driver driver = driverMapper.selectByDriverId(driverId1);
//            设置和用户聊天的那个的名字
                String passengerName = passenger.getName();

                String chatRoomName ="乘客：" + passengerName+" "+ "司机："+driver.getName();

//                是三方聊天室，管理员就这样设置
                chatRoom.setName(chatRoomName);

            } else {
//                是司机和管理员的聊天室，管理员
                Driver driver = driverMapper.selectByDriverId(driverId1);
                chatRoom.setName("司机："+driver.getName());

            }
            chatRooms1.add(chatRoom);
        }

        return chatRooms1;
    }

    //     根据乘客和司机的id查看聊天室
    @Override
    public ChatRoom searchChatRoomByPassengerIdAndDriverId(Integer passengerId, Integer driverId) {


        return chatRoomMapper.searchChatRoom(passengerId, driverId);


    }

    //    创建一个聊天室
    @Override
    public ChatRoom createChatRoom(Passenger self, Driver other) {

//        先判断有无聊天室的存在
        ChatRoom chatRoom = searchChatRoom(self, other);

//        再看看要是否创建一个。

//        需要创建一个
        if (chatRoom == null) {

            ChatRoom chatRoom1 = new ChatRoom();

            chatRoom1.setPassengerId(self.getPassengerId());
            chatRoom1.setDriverId(other.getDriverId());

            chatRoom1.setId(chatRoomMapper.insertChatRoom(chatRoom1));


            return chatRoom1;
        }
//        不需要创建一个
        else return chatRoom;

    }

    //    乘客和管理员
    @Override
    public ChatRoom createChatRoom(Passenger self, Integer managerId) {


//        先判断有无聊天室的存在
        ChatRoom chatRoom = searchChatRoom(self, managerId);

//        再看看要是否创建一个。

//        需要创建一个
        if (chatRoom == null) {

            ChatRoom chatRoom1 = new ChatRoom();

            chatRoom1.setPassengerId(self.getPassengerId());
            chatRoom1.setManagerId(managerId);

            chatRoom1.setId(chatRoomMapper.insertChatRoom(chatRoom1));


            return chatRoom1;
        }
//        不需要创建一个
        else return chatRoom;
    }

    //    司机和管理员
    @Override
    public ChatRoom createChatRoom(Driver self, Integer managerId) {


//        先判断有无聊天室的存在
        ChatRoom chatRoom = searchChatRoom(self, managerId);

//        再看看要是否创建一个。

//        需要创建一个
        if (chatRoom == null) {

            ChatRoom chatRoom1 = new ChatRoom();

            chatRoom1.setDriverId(self.getDriverId());
            chatRoom1.setManagerId(managerId);

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

    //    查找聊天室，乘客和管理员
    @Override
    public ChatRoom searchChatRoom(Passenger passenger, Integer managerId) {

        return chatRoomMapper.searchChatRoomBypassengerIdAndManagerId(passenger.getPassengerId(), managerId);
    }

    //    查找聊天室，司机和管理员
    @Override
    public ChatRoom searchChatRoom(Driver driver, Integer managerId) {

        return chatRoomMapper.searchChatRoomByDriverIdAndManagerId(driver.getDriverId(), managerId);

    }


    //查看聊天室，司机，乘客，管理员
    @Override
    public ChatRoom searchChatRoom(Driver driver, Passenger passenger, Integer managerId) {


        return chatRoomMapper.searchChatRoomByDriverIdAndManagerIdAndPassengerId(
                driver.getDriverId(), passenger.getPassengerId(), managerId);

    }

    @Override
    public ChatRoom searchChatRoom(Integer driverId, Integer passengerId, Integer managerId) {
        return chatRoomMapper.searchChatRoomByDriverIdOrManagerIdOrPassengerId(
                driverId, passengerId, managerId);
    }


}
