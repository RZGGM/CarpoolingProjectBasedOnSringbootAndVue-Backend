package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.DriverSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverSerivceImpl implements DriverSerivce {

    @Autowired
    DriverMapper driverMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Integer register(Driver driver) {


        return driverMapper.insert(driver);
    }

    @Override
    public Integer remove(Integer id) {
        return null;
    }

    @Override
    public Integer mod(Driver newDriver) {
        return null;
    }

    @Override
    public Driver search(Integer id) {
        return null;
    }

    @Override
    public List<Driver> searchAll() {
        return driverMapper.selectAll();
    }

    @Override
    public Integer modAppraise(Integer appraiseId) {
        return null;
    }

    @Override
    public Integer addOrderToDriver(String passengerOrderId, Integer driverId) {

//        查询到乘客的订单
        Order order = orderMapper.selectByOrderId(passengerOrderId);
//        判断这个订单是否有司机了
        if (order.getDriverId() != -1) {
//            已经有司机接取，就会进入到这
            return OrderState.HAS_DRIVER;
        } else {
//       没有司机进入到这
//       要先修改下乘客的订单，更新司机到乘客的订单里。
            order.setDriverId(driverId);
            orderMapper.updateOrderDriverOrPassenger(order);
//       再然后，为司机生成订单
            Order orderDriver = new Order();
            orderDriver.setCreateUserType(UserType.DRIVER);
            orderDriver.setOtherId(order.getOrderId());
//       插入订单给司机
            return orderMapper.insertOrderById(orderDriver);


        }

    }
}
