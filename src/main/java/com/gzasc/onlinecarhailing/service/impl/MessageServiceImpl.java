package com.gzasc.onlinecarhailing.service.impl;


import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.MessageMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.Message;
import com.gzasc.onlinecarhailing.pojo.UserType;
import com.gzasc.onlinecarhailing.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;
    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    DriverMapper driverMapper;

    @Override
    public Integer createMessage(Message message) {


//
//        根据信息所有者的id和类型找出并且：设置所有者的名字， 所以一个必要所有者类型和所有者id和data。
//        判断所有者的类型
        if (message.getOwnerType().equals(UserType.PASSENGER)) {
//            所有者为乘客进入到这
            message.setOwnerName(passengerMapper.selectById(message.getOwnerId()).getName());

        } else if (message.getOwnerType().equals(UserType.DRIVER)) {

//            所有者为司机
            message.setOwnerName(driverMapper.selectById(message.getOwnerId()).getName());

        } else {
//            所有者为管理员，管理员没有名字
            message.setOwnerName("管理员" + (message.getOwnerId().toString()));

        }


        return messageMapper.insertMessage(message);
    }

    //     根据聊天室的id查找消息
    @Override
    public List<Message> selectMessagesByChatRoomId(Integer chatRoomId) {

        List<Message> messages = messageMapper.selectMessagesByChatRoomId(chatRoomId);

        if (messages == null) return null;

        if (messages.isEmpty()) return null;

//        判断是否要更新，用第一个来判断
        if (messages.get(0).getOwnerName() == null) {

            List<Message> messageList = new ArrayList<>();

            for (Message message : messages) {

//            根据消息所有者的类型调用mapper去根据所有者的id找到所有者的名字，将所有者的名字更新好。
//        根据信息所有者的id和类型找出设置所有者的名字
//        判断所有者的类型
                if (message.getOwnerType().equals(UserType.PASSENGER)) {
//            所有者为乘客进入到这
                    message.setOwnerName(passengerMapper.selectById(message.getOwnerId()).getName());
                    messageMapper.updateMessage(message);

                } else if (message.getOwnerType().equals(UserType.DRIVER)) {

//            所有者为司机
                    message.setOwnerName(driverMapper.selectById(message.getOwnerId()).getName());
                    messageMapper.updateMessage(message);

                } else {
//            所有者为管理员，管理员没有名字
                    message.setOwnerName("管理员" + (message.getOwnerId().toString()));
                    messageMapper.updateMessage(message);

                }
                messageList.add(message);

            }
            return messageList;
        } else return messageMapper.selectMessagesByChatRoomId(chatRoomId);
    }
}
