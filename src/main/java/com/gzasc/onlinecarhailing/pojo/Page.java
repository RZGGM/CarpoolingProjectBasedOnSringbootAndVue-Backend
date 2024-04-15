package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;
import lombok.ToString;

@Data  //这个会自动生成get与set方法
@ToString  //这个会自动生成toString方法
public class Page {
// 辅助分布的类，用来记录页数和
//    当前页数
    private int pageNum;
//    每页的数据的个数
    private int pageSize;


}
