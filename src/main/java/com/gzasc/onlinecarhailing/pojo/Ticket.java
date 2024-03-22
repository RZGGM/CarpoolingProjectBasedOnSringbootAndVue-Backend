package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//票类，这个应该是一个已经有司机的特殊的订单类。

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

//    ID
    private Integer id;
//    司机
    private Driver driver;

//    最大乘客数
    private Integer passengerCount;
//    乘客
    private List<Passenger> passengerList;

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
//    价格
    private float price;

//    状态，






}
