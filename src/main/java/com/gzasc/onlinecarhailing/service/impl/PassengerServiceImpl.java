package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.PassengerMapper;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;


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


        return passengerMapper.deleteByIds(ids);
    }

    @Override
    public Integer mod(Passenger newPassenger) {
        return passengerMapper.update(newPassenger);
    }

    @Override
    public Passenger search(Integer id) {
        return null;
    }

    @Override
    public List<Passenger> searchAll() {
        return null;
    }
}
