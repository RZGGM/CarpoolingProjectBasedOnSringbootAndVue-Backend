package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

@Data
public class User {

    //    唯一标识ID
    private Integer id;
    //    手机号
    private String phone;
    //    性别
    private String gender;

    // 公司标识
    private String companyId;


    //    状态
    private Integer State;

    //    姓名
    private String name;

    //    帐号
    private Account account;


}
