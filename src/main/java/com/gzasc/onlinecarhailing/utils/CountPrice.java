package com.gzasc.onlinecarhailing.utils;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;

@Component
public class CountPrice {

    public Integer countPrice( String departureAddress, String destinationAddress){

//        根据地址计算价格，不知道怎么定价格，只学过写代码。


//        先直接返回吧。
        return  RandomUtil.randomInt(10, 9999999);

    }
// 根据人数计算价格
    public Integer countPriceOfPassengerCount(Integer passengerCount, Integer price){



        return passengerCount * price;
    }


}
