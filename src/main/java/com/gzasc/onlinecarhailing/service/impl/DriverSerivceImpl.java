package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.DriverSerivce;
import com.gzasc.onlinecarhailing.utils.OrderUtils;
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
        return driverMapper.update(newDriver);
    }

    @Override
    public Driver search(Integer id) {
        return driverMapper.selectById(id);
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
        if ( order.getDriverId() != -1) {
//            已经有司机接取，就会进入到这
            return OrderState.HAS_DRIVER;
        } else {
//       没有司机进入到这
//       要先修改下乘客的订单，更新司机到乘客的订单里。
            //将乘客的订单的状态设置为：已经有司机了，所以其它司机不可以看到这个订单
            order.setDriverId(driverId);
//            先判断下这个订单是否接受拼单
            if (order.getJoinOrderCanJoin().equals(JoinOrderCanJoin.CAN_JOIN_ORDER)){
//              接受拼车进入到这
                order.setOrderType(OrderType.PASSENGER_AND_DRIVER_WAIT_OTHER_PASSENGER);

            }else order.setOrderType(OrderType.PASSENGER_AND_DRIVER_NOWAIT);
//                设置乘客的订单的状态为等待出行。
            order.setState(OrderState.WAIT_DEPART);

//       再然后，为司机生成订单
            Order orderDriver = OrderUtils.onPassengerCreateJoinOrderToCreateForDriver(order, driverId);
// 为乘客的订单更新对应的司机的订单的编号
            order.setOtherId(orderDriver.getOrderId());
//            将司机订单更新对应的乘客订单
            orderDriver.setOtherId(order.getOrderId());
// 设置司机订单的乘客的id
            orderDriver.setPassengerId(order.getId());

// 更新乘客的订单
            orderMapper.updateOrderDriverOrPassenger(order);

            orderDriver.setOtherId(order.getOrderId());
//       插入订单给司机
            return orderMapper.insertOrder(orderDriver);


        }

    }
}
