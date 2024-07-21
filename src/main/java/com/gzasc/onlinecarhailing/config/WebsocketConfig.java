package com.gzasc.onlinecarhailing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
//        注册ServerEndpointExporter的实例到spring容器中，
//        这个实例会自动扫描使用了@ServerEndpoint注解的类。
        return new ServerEndpointExporter();
    }

}
