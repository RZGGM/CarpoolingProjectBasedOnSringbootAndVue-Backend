package com.gzasc.onlinecarhailing;

import com.gzasc.onlinecarhailing.utils.GeneratorJWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
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
    @Test
    public void testJWT(){

        Map<String, Object> userMap = new HashMap<>();


        userMap.put("id", 1);
        userMap.put("account", "112233");
        userMap.put("type", 1);

        System.out.println("加密前的"+userMap);

//        String jwt = GeneratorJWTUtils.generateJWT(userMap);

        String jwt = GeneratorJWTUtils.generateJWT(userMap);

        System.out.println("加密后的："+jwt);

        System.out.println("解密后的："+GeneratorJWTUtils.parseClaim(jwt));

//        解密后的：header={typ=JWT, alg=HS256},payload={id=1, account=112233, jti=ed936896-f9f6-4cb9-8fc4-089ddc7bdb99, exp=1712131488, iat=1711872288, iss=zr, sub=user},signature=TxdgmPxGrl6tPQmZkH2Ef0_HvDEMkgLxt4tqLJuxHPU

    }

}
