package com.gzasc.onlinecarhailing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//投诉类
public class Appeal {

//    id
    private Integer id;
//    被投诉的订单的id
    private Integer orderId;
//    投诉发起人
    private Integer userId;
//    投诉的内容
    private String content;

}
