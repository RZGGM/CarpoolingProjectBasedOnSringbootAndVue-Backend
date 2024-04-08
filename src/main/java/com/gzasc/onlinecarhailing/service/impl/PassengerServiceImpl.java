package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.AccountMapper;
import com.gzasc.onlinecarhailing.Mapper.OrderMapper;
import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.Mapper.TicketMapper;
import com.gzasc.onlinecarhailing.pojo.*;
import com.gzasc.onlinecarhailing.service.PassengerService;
import com.gzasc.onlinecarhailing.service.TicketService;
import com.gzasc.onlinecarhailing.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AccountMapper accountMapper;


    //    基于司机发出的拼车订单生成订单
    @Override
    public Integer addOrderOnJoinOrderToPassenger(Order driverCreateJoinOrder, Order passengerOrder){

//        判断司机的拼车订单的状态是否为已经满员了，如果是就直接返回：订单已经满了。.
//        直接就是PASSENGER_COUNT_MAX用来判断空位不足和数量已经满了。
        if (driverCreateJoinOrder.getState().equals(OrderState.PASSENGER_COUNT_MAX)) return OrderState.PASSENGER_COUNT_MAX;

        // 判断司机的拼车订单的剩余人数是否足够，如果足够就生成订单不行返回订单已经满了。，同时将状态设置为：人员满了。
        if (driverCreateJoinOrder.getManCount() >= passengerOrder.getPassengerCount()){
//            进入到这里说明是可以的
//            为乘客生成订单
            Order orderPassenger = OrderUtils.onDriverCreateJoinOrderToCreateForPassenger(
                    driverCreateJoinOrder, passengerOrder
            );
// 为司机生成订单
            Order orderDriver = OrderUtils.onPassengerOrderToCreateForDriver(driverCreateJoinOrder, orderPassenger);
// 再修改乘客的订单的对应的司机的订单的编号
            orderPassenger.setOtherId(orderDriver.getOrderId());

//            修改基本的拼车的订单
            driverCreateJoinOrder.setManCount(driverCreateJoinOrder.getManCount() - passengerOrder.getPassengerCount());

//            判断下要不要修改状态
            if (0 == driverCreateJoinOrder.getManCount()){
//                如果没有空位了，就修改状态为人数已经满了。
                driverCreateJoinOrder.setState(OrderState.PASSENGER_COUNT_MAX);
            }

//            更新到数据库
            orderMapper.updateOrderDriverOrPassenger(driverCreateJoinOrder);
            orderMapper.insertOrder(orderDriver);

            return orderMapper.insertOrder(orderPassenger);


        }else {

            if (driverCreateJoinOrder.getManCount() == 0){
                driverCreateJoinOrder.setState(OrderState.PASSENGER_COUNT_MAX);


                orderMapper.updateOrder(driverCreateJoinOrder.getOrderId(), OrderState.PASSENGER_COUNT_MAX);

                return OrderState.PASSENGER_COUNT_MAX;

            }


            return OrderState.PASSENGER_COUNT_MAX;
        }



    }

    //    接受订单
    @Override
    public Integer addOrderToPassenger(String driverOrderId, Integer passengerId){

//        查询到司机的订单
            Order order = orderMapper.selectByOrderId(driverOrderId);
//        判断这个订单是否活满员了
            if (Objects.equals(order.getState(), OrderState.PASSENGER_COUNT_MAX)) {
//            已经有司机接取，就会进入到这
                return OrderState.PASSENGER_COUNT_MAX;
            } else {
//       没有满员
//       要先修改下司机的拼车订单，同时为司机新增一个订单，每有一个乘客通过拼车订单就修改下拼车订单，同时依据拼车订单为司机新增订单。
//                1。判断下拼车订单能接取的人数。
                Integer maxPassengerCount = order.getManCount();
                if (maxPassengerCount > 0){

//                    manCount用来判断能接单的人数，当它大于0，说明还能接单
//                    然后依据拼单订单为司机和乘客生成新的订单
                    Order passengerOrder = OrderUtils.onDriverCreateJoinOrderToCreateForUser(order, passengerId);
                    Order driverOrder = OrderUtils.onDriverCreateJoinOrderToCreateForUser(order, order.getOwnerId());
                    passengerOrder.setOtherId(driverOrder.getOtherId());
                    driverOrder.setOtherId(passengerOrder.getOtherId());
//                    插入到数据库
                    orderMapper.insertOrder(passengerOrder);
                    orderMapper.insertOrder(driverOrder);

//                    再根据拼车的基本订单的可接受人数，看是否要更改为：订单人数已经满了。
                    if (maxPassengerCount - 1 == 0){
                        order.setState(OrderState.PASSENGER_COUNT_MAX);
                        order.setManCount(0);
                    }

                }



//       再然后，为司机生成订单
                Order orderDriver = OrderUtils.onPassengerCreateJoinOrderToCreateForDriver(order, passengerId);
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
    @Override
    public Integer register(Passenger passenger) {

        return passengerMapper.insert(passenger);
    }

    @Override
    public Integer remove(Integer id) {
        return passengerMapper.deleteById(id);
    }

    @Override
    public Integer removeByIds(List<Integer> ids) {
//        先清除乘客对应的订单，和发起的拼单
        Passenger passenger;
        List<Order> orders ;
        List<Integer> orderIds = new ArrayList<>();
        List<Integer> accountIds = new ArrayList<>() ;
        for (Integer passengerId : ids) {

            passenger = passengerMapper.selectById(passengerId);

            if (null == passenger) return 0;
//            因为乘客和订单的关系是一对多的关系，所以只需要在订单表里查询订单记录的乘客的id就行。
//  乘客类里记录订单的信息只是方便我做前端。
            orders = orderMapper.selectBySelfId(passengerId);

            accountIds.add(passenger.getAccountId());

            if (null != orders && !orders.isEmpty()) {

                for (Order order : orders) {
                    orderIds.add(order.getId());
                }
//                删除乘客对应的订单
                orderMapper.deleteOrdersByIds(orderIds);

            }
//            从帐号表中对绑定的乘客的身份进行除名。
            accountMapper.updateAccountsPassengerIdToNullByAccountIds(accountIds);
//            这里应该还有当，帐号对应的乘客和司机身份都删除时，帐号也自动清空？
//            还是说可以再次注册身份？

        }

//  最后删除乘客的信息。
        return passengerMapper.deleteByIds(ids);
    }

    @Override
    public Integer mod(Passenger newPassenger) {
        return passengerMapper.update(newPassenger);
    }

    @Override
    public Passenger search(Integer id) {
        return passengerMapper.selectById(id);
    }

    @Override
    public List<Passenger> searchAll() {



        return passengerMapper.selectAll();
    }


}
