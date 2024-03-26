package com.gzasc.onlinecarhailing.service;

import com.gzasc.onlinecarhailing.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// 车辆服务层
public interface CarService {
//增加车辆
    Integer addCar(Car car);
//通过id删除一辆车
    Integer remove(Integer id);
//    通过id的集合批量删除车
    Integer removeByIds(List<Integer> ids);
//    修改车的信息
    Integer mod(Car car);
//    通过id查询车
    Car searchById(Integer id);
//    通过所有者的id查询所有的车
    List<Car> searchByOwnerId(Integer ownerId);
//    查询所有的车辆
    List<Car> searchAll();

}
