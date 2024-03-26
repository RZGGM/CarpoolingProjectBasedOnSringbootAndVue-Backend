package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//评价
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appraise {

//    id
    private Integer id;
//    订单id
    private Integer orderId;
//    司机id
    private Integer driverId;
//    评价的内容
    private String comment;

}
