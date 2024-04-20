package com.gzasc.onlinecarhailing.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.utils.GeneratorJWTUtils;
import io.netty.util.internal.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class UserCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getRequestURL().toString();
        String jwt = req.getHeader("Jwt");

//        如果请求的路径是login直接放行
        if (url.contains("login")) {

            return true;

        }


//        判断是否有令牌
        if (!StringUtils.hasLength(jwt)) {
// 没有令牌进入到这
            resp.getWriter().write(JSONObject.toJSONString(Result.error("请登录")));
            return false;
        }

        System.out.println("");

//        解析Jwt
        try {

            GeneratorJWTUtils.parseClaim(jwt);

//            log.info(url);


        } catch (Exception exception) {
//            解析失败进入到这

            resp.getWriter().write(JSONObject.toJSONString(Result.error("请登录")));
            return false;
        }


//放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
