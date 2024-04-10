package com.gzasc.onlinecarhailing.service.impl;


import com.gzasc.onlinecarhailing.Mapper.MessageMapper;
import com.gzasc.onlinecarhailing.pojo.Message;
import com.gzasc.onlinecarhailing.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Integer createMessage(Message message) {
        return messageMapper.insertMessage(message);
    }
    //     根据聊天室的id查找消息
    @Override
    public List<Message> selectMessagesByChatRoomId(Integer chatRoomId){

        return messageMapper.selectMessagesByChatRoomId(chatRoomId);
    }
}
