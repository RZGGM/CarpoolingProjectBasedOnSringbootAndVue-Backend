package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Message;
import org.springframework.stereotype.Service;


public interface MessageService {

//插入信息
     Integer createMessage(Message message);
}
