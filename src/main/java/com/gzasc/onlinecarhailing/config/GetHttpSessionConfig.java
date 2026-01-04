package com.gzasc.onlinecarhailing.config;


import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

// 用于在会话建立时保存会话。
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    //    这个方法会在建立连接时自动调用。
//    作用是保存 session，也就是服务端和客户端的所特有的信息，每个浏览器和后端连接的session都是不一样的。可以用它来区分和识别。
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        System.out.println("正在建立起websocket连接。。。");
        super.modifyHandshake(sec, request, response);
//        获取会话对象，
        HttpSession httpSession = (HttpSession) request.getHttpSession();
//        保存httpSession
        try {
            sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
