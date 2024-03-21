package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

//票类

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

//    ID
    private Integer id;

//    预计出发地详细地址，这也许是一个类。
    private Address departureAddress;

//    预计出发地点经度，虽然政府的标准要，但我没学会怎么获取，先忽略吧。
//    private Integer depLongitude;
////    纬度，同上，忽略
//    private Integer depLatitude;

//    预计目的地
    private Address destinationAddress;

    //    预计目的地经度，虽然政府的标准要，但我没学会怎么获取，先忽略吧。
//    private Integer destLongitude;
//    //    纬度，同上，忽略
//    private Integer destLatitude;

//    发车时间
    private Date startTime;





}
