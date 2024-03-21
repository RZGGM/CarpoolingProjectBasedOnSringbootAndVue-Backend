package com.gzasc.onlinecarhailing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCarHailingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineCarHailingApplication.class, args);

        System.out.println("hello, wlal");

    }


}
