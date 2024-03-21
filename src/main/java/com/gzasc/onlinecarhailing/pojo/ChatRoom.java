package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//聊天室
@Data
@NoArgsConstructor
public class ChatRoom {

//    id
    private Integer id;
//    聊天室的名字
    private String name;
//  用户间的交流的信息
    private List<Message> messageList;
//    创建时间，方便以后自动销毁
    private Date createDate;
//    应该还有聊天室用户的id，方便查找

    private Integer selfId;
    private Integer otherId;

    public ChatRoom(String name, List<Message> messageList, Date createDate) {
        this.name = name;
        this.messageList = messageList;
        this.createDate = createDate;
    }
}
