package com.gzasc.onlinecarhailing.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.channels.Pipe;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

//    id
    private Integer id;
//    车辆车牌号
    private String vehicleNo;
//    车辆所有人
    private Integer ownerId;
//车身颜色
    private String color;

}
