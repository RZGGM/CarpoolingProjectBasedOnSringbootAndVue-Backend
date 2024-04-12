package com.gzasc.onlinecarhailing.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

//系统中各用户交流发的信息的类
@Data
public class Message {

//    id
    private Integer id;
//    所属用户的ID
    private Integer ownerId;
//    内容
    private String data;
//    对就的聊天室id
    private Integer chatRoomId;
//    所有者的类型
    private Integer ownerType;
//    所有者的名字
    private String ownerName;
//    发送的时间
    private Date sendTime;

}
