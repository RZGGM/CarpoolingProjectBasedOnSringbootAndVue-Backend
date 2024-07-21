# 包说明
前端请求在进入到controller层前的拦截，然后进行验证，或是其它操作。
## 要启用拦截器要设置好config
```java
package com.gzasc.onlinecarhailing.config;

import com.gzasc.onlinecarhailing.interceptor.UserCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    UserCheckInterceptor userCheckInterceptor;

//    配置要拦截的路径。
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userCheckInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login/**");
    }
}

```
