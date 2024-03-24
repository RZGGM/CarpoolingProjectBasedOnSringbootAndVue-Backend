package com.gzasc.onlinecarhailing.service;


import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.Account;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//对乘客功能的实现
public interface PassengerService {

    //    注册帐号
     Integer register(Passenger passenger);
    //    删除，通过id
     Integer remove(Integer id);
//    批量删除
     Integer removeByIds(List<Integer> ids);
    //    修改
     Integer mod(Passenger newPassenger);
    //    根据id查询
     Passenger search(Integer id);
    //    查询所有
     List<Passenger> searchAll();

}
