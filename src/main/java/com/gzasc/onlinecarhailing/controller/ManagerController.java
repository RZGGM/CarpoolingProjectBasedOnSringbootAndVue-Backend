package com.gzasc.onlinecarhailing.controller;

import com.gzasc.onlinecarhailing.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//管理员接口
@Controller
public class ManagerController {

    //    删除乘客要可以多选，批量删除
    @RequestMapping("/deletePassengerByIds")
    public Result deleteByIds(List<Integer> ids){

        

        return null;
    }

}
