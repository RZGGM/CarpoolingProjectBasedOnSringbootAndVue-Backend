package com.gzasc.onlinecarhailing.service;


import com.gzasc.onlinecarhailing.pojo.Appeal;

import java.util.List;

public interface AppealSevice {

    //   向订单创建投诉
    Integer addAppeal(Appeal appeal);

    //    删除投诉
    Integer removeAppeal(Integer appealId);

    //    修改投诉内容
    Integer modAppeal(Appeal appeal);

    //  根据投诉的id返回投诉
    Appeal searchAppealByAppealId(Integer appealId);

    //    根据订单的id返回它的投诉信息
    Appeal searchAppealByOrderId(Integer orderId);

    //    根据用户的id查询它所发起的投诉
    List<Appeal> searchAppealsByUserId(Integer userId);
//
}
