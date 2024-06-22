package com.gzasc.onlinecarhailing.config;


import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.context.annotation.Configuration;

// 用于在会话建立时保存会话。
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

//    这个方法会在建立连接时自动调用。
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        System.out.println("正在建立起websocket连接。。。");
        super.modifyHandshake(sec, request, response);

//        获取会话对象，
        HttpSession httpSession = (HttpSession) request.getHttpSession();
//        保存httpSession
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);

    }
}
