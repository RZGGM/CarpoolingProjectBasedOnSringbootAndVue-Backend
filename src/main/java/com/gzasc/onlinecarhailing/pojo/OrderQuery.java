package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;
import lombok.ToString;

// 用来辅助分布查询的类
@Data
@ToString
public class OrderQuery extends Page {

    //    创建这个订单的用户的类型
    private Integer createUserType;
    //    创建这个订单的用户的id
    private Integer createUserId;

}
