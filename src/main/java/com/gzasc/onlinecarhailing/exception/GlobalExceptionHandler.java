package com.gzasc.onlinecarhailing.exception;

import com.gzasc.onlinecarhailing.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {

//    捕获全部异常的方法，Exception.class是所有异常的父类。
    @ExceptionHandler(Exception.class)
    public Result exceptionHandle(Exception exception){

        exception.printStackTrace();
        System.out.println("出错误了。");
        return Result.error("系统异常");
    }


}
