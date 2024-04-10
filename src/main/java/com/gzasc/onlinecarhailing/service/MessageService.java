package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Message;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessageService {

//插入信息
     Integer createMessage(Message message);

//     根据聊天室的id查找消息
     List<Message> selectMessagesByChatRoomId(Integer chatRoomId);

}
