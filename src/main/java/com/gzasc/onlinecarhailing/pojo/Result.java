package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.alibaba.fastjson.JSONObject;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

//统一返回给前端的类型。
@Data
@AllArgsConstructor

public class Result {


//    响应码，1代表成功，0代表失败
    private Integer code;
//    提示信息
    private String msg;
//    返回的数据
    private Object data;
//    token 现在就先用来存JWT吧
    private String jwt;

    public Result(){}
    //    因为多加了个属性，所以得自己再写个构造方法，
//    因为@AllArgsConstructor只会生成全参的构造方法。
//    而之前用的大多是使用三个参数的。
    public Result(int i, String msg, Object data){
        this.code = i;
        this.msg = msg;
        this.data = data;
    }


    public static Result successHaveJwt(String msg, Object data, String jwt){

        return new Result(1, msg, data, jwt);

    }

    public static Result success(Object data){

        return new Result(1,"成功", data);

    }
    public static Result success(){

        return new Result(1,"成功", null);

    }
    public static Result success(String msg){

        return new Result(1,msg, null);

    }
    public static Result success(String msg, Object data){

        return new Result(1,msg, data);

    }
    public static Result error(String msg){

        return new Result(0,msg, null);

    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }


}
