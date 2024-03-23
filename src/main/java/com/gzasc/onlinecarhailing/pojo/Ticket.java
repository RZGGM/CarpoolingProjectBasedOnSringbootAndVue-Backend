package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//票类，这个应该是一个已经有司机的特殊的订单类。这个数据库应该不需要一个表来存储。

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

//    ID
    private Integer id;
////    司机
//    private Driver driver;

//    最大乘客数
    private Integer passengerCount;
////    乘客
//    private List<Passenger> passengerList;

//    预计出发地详细地址，这也许是一个类。
//    因为它是可以沿路上下的，所以它应该是可以选择的。
    private List<Address> departureAddress;

//    预计出发地点经度，虽然政府的标准要，但我没学会怎么获取，先忽略吧。
//    private Integer depLongitude;
////    纬度，同上，忽略
//    private Integer depLatitude;

//    预计目的地
    private List<Address> destinationAddress;

    //    预计目的地经度，虽然政府的标准要，但我没学会怎么获取，先忽略吧。
//    private Integer destLongitude;
//    //    纬度，同上，忽略
//    private Integer destLatitude;

//    预计发车时间
    private Date startTime;
//    价格
    private float price;

//    状态，设置是否可购买，0不可以购买，1可以。
//      状态的改变有，满客变为不可购买，和已经发车，所以不可以购买。
    private Integer status;







}
