package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//地址类
@Data
public class Address {

//    id
    private Integer id;

//    市
    private String city;

//    县或是区
    private List<County> counties;
//    镇
    private List<Town> towns;

//    详细地址对应数据库里的自定义地址。
    private String detailAddress;






}
