package com.gzasc.onlinecarhailing.pojo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//票类，这个应该是一个已经有司机的特殊的订单类。这个数据库应该不需要一个表来存储。

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

    //    ID
    private Integer id;
////    司机
//    private Driver driver;

    //    最大乘客数
    private Integer passengerCount;
////    乘客
//    private List<Passenger> passengerList;

    //    已经售出的数量
    private Integer soldCount;

    //    预计出发地详细地址，这也许是一个类。
//    因为它是可以沿路上下的，所以它应该是可以选择的。
//    不要是一个类，不然太麻烦了。直接就是String吧。
//    一个有格式的String，县级-镇级-出发点1-出发点2-。。。这样的。
    private String departureAddress;

//    预计出发地点经度，虽然政府的标准要，但我没学会怎么获取，先忽略吧。
//    private Integer depLongitude;
////    纬度，同上，忽略
//    private Integer depLatitude;

    //    预计目的地
//    一个有格式的String，县级-镇级-出发点1-出发点2-。。。这样的。
//    后面再用一个工具类，将它给分割了。
    private String destinationAddress;

    //    预计目的地经度，虽然政府的标准要，但我没学会怎么获取，先忽略吧。
//    private Integer destLongitude;
//    //    纬度，同上，忽略
//    private Integer destLatitude;

    //    预计发车时间
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    //    价格
    private float price;

    //    状态，设置是否可购买，0不可以购买，1可以。
//      状态的改变有，满客变为不可购买，和已经发车，所以不可以购买。
    private Integer status;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
