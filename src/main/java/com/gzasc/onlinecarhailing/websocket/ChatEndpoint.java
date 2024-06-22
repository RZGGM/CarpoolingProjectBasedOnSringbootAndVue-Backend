package com.gzasc.onlinecarhailing.websocket;


import com.alibaba.fastjson.JSON;
import com.gzasc.onlinecarhailing.config.GetHttpSessionConfig;
import com.gzasc.onlinecarhailing.pojo.ChatRoom;
import com.gzasc.onlinecarhailing.pojo.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocketChat", configurator = GetHttpSessionConfig.class)
@Component
public class ChatEndpoint {

    //    因为这个类时多例的，所以要定义一个共用的空间来保存session，在用户间发送消息时要用到。
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();
    //    定义一个属性用于记录当前所在的会话对象。
    private HttpSession httpSession;

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        System.out.println("服务端websocket已经打开了。。");
//        将session保存
        this.httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
//        得到此会话对应的身份的聊天室所有id。
        String charRoomsIdsStr = (String) this.httpSession.getAttribute("charRoomsIdsStr");
        onlineUsers.put(charRoomsIdsStr, session);
//        （可选）在websoket的会话建立时，可以向前端反馈。不过前端也有onOpen事件在连接建立时被触发。

    }

    @OnClose
    public void onClose(Session session) {
//        服务端退出会话时，要从服务端保存的会话列表中去除相应的会话对象。
        //        得到此会话对应的身份的聊天室所有id同时也是标识
        String charRoomsIdsStr = (String) this.httpSession.getAttribute("charRoomsIdsStr");

        onlineUsers.remove(charRoomsIdsStr);

//        （可选）通知其它会话这个会话已经下线。

    }

    //    前端发送来的数据一般可以用String类型接收，然后转成自己需要的类型
    @OnMessage
    public void onMessage(String message) throws EncodeException, IOException {
//        将消息转类型
        Message message1 = JSON.parseObject(message, Message.class);
//        将消息发给目标客户端
//        得到目标聊天室的id
        Integer goalChatroomId = message1.getChatRoomId();
//        发送给目标
//        遍历所有的key，判断用哪个session。
        for (String charRoomsIdsStr : onlineUsers.keySet()) {

            // 使用split方法将字符串分割成数组
            String[] stringArray = charRoomsIdsStr.split(",");
            // 使用Stream API将字符串数组转换为Integer列表
            List<Integer> integerList = Arrays.stream(stringArray)
                    .map(Integer::parseInt).toList(); // 收集到List中
// 判断下是否是这个key，是就得到session
            if (integerList.contains(goalChatroomId)) {

                Session session = onlineUsers.get(charRoomsIdsStr);
//                发送数据。
                session.getBasicRemote().sendText(JSON.toJSONString(message1));

            }

        }

    }


}
