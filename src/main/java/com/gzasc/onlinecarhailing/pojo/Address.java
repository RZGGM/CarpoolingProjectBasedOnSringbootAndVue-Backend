package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

//地址类
@Data
public class Address {

//    id
    private Integer id;

//    市
    private String city;

//    县或是区
    private String region;
//    镇

    private String town;

//    道路或是街道

    private String street;

//    门牌号或是店名或是编号，可以选

    private Integer number;






}
