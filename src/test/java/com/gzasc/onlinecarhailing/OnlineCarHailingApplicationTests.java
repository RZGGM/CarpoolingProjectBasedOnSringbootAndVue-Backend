package com.gzasc.onlinecarhailing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
//springboot项目运行可能需要数据库连接才能运行，如果在配置文件中设置了数据库的信息，可以删掉下面的注解再试试
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

class OnlineCarHailingApplicationTests {

    @Test
    public void test1(){

//        结果是相等
        if (Objects.equals(1,1)) System.out.println("相等");

    }

}
