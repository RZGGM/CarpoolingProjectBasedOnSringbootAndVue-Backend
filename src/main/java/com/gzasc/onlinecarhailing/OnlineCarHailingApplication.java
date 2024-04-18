package com.gzasc.onlinecarhailing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class OnlineCarHailingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineCarHailingApplication.class, args);

        System.out.println("hello, wlal");

    }


}
