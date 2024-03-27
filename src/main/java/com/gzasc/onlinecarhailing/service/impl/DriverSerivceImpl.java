package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.DriverMapper;
import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.service.DriverSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverSerivceImpl implements DriverSerivce {

    @Autowired
    DriverMapper driverMapper;

    @Override
    public Integer register(Driver driver) {

        driverMapper.insert(driver);

        return null;
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
        return null;
    }

    @Override
    public Integer modAppraise(Integer appraiseId) {
        return null;
    }
}
