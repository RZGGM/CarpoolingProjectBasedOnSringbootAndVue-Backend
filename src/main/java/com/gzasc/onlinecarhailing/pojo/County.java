package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//县
@Data
@AllArgsConstructor
@NoArgsConstructor
public class County {

//  地级市的ID，固定为湛江
private Integer cityId;
//自己的id
    private Integer id;
//    名字
    private String name;


}
