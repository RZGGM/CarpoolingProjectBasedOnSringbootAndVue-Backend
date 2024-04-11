package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//管理用户信息的
@Mapper
public interface MessageMapper {

//    发出一条信息。

     Integer insertMessage(Message message);

    // 根据聊天室的id查找它对应的信息
    List<Message> selectMessagesByChatRoomId(Integer chatRoomId);

//    更新信息
    Integer updateMessage(Message message);


}
