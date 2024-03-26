package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.CarMapper;
import com.gzasc.onlinecarhailing.pojo.Car;
import com.gzasc.onlinecarhailing.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

    @Override
    public Integer addCar(Car car) {



        return  carMapper.insertCar(car);
    }

    @Override
    public Integer remove(Integer id) {
        return carMapper.deleteCarById(id);
    }

    @Override
    public Integer removeByIds(List<Integer> ids) {
        return carMapper.deleteCarsByIds(ids);
    }

    @Override
    public Integer mod(Car car) {
        return carMapper.updateCar(car);
    }

    @Override
    public Car searchById(Integer id) {
        return carMapper.selectById(id);
    }

    @Override
    public List<Car> searchByOwnerId(Integer ownerId) {
        return carMapper.selectByOwnerId(ownerId);
    }

    @Override
    public List<Car> searchAll() {
        return carMapper.selectAllCar();
    }
}
