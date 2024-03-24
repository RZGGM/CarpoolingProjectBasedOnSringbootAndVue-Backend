package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Driver;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverMapper {

    //    增加
    public Integer insert(Driver driver);
    //    删除，通过id删除
    public Integer deleteById(Integer id);
    //    修改
    public Integer update(Driver driver);
    //    查询，根据司机的id查一条
    public Driver selectById(Integer id);
    //    查询所有
    public List<Driver> selectAll();

}
