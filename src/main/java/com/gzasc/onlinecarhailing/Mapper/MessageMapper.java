package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

//管理用户信息的
@Mapper
public interface MessageMapper {

//    发出一条信息。

    public Message insertMessage(Message message);

}
