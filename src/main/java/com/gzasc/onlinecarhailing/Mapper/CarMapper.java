package com.gzasc.onlinecarhailing.Mapper;


import com.gzasc.onlinecarhailing.pojo.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对车辆数据库的绑定
@Mapper
public interface CarMapper {

//增加车辆

    Integer insertCar(Car car);
//    删除车辆一辆
    Integer deleteCarById(Integer id)  ;
//    批量删除
    Integer deleteCarsByIds(List<Integer> ids);
//    修改车辆信息
    Integer updateCar(Car car);
//    通过id查询车
    Car selectById(Integer id);
//    查看所有的车辆
    List<Car> selectAllCar();
//    查看自己所有的车辆
    List<Car> selectByOwnerId(Integer id);

}
