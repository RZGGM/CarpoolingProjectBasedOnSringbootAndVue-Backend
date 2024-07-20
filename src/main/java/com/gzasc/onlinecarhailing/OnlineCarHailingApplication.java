package com.gzasc.onlinecarhailing;


import com.gzasc.onlinecarhailing.service.TicketService;
import com.gzasc.onlinecarhailing.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnlineCarHailingApplication {


    public static void main(String[] args) {

        SpringApplication.run(OnlineCarHailingApplication.class, args);

        System.out.println("hello, wlal");


    }


}
