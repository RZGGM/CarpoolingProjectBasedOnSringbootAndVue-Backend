package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Order;
import com.gzasc.onlinecarhailing.pojo.Passenger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//操作乘客的数据库
@Mapper
public interface PassengerMapper {

//    增加
     Integer insert(Passenger passenger);
//    删除，通过id删除，多个
     Integer deleteByIds(List<Integer> ids);
//    删除一个
     Integer deleteById(Integer id);
//    修改
     Integer update(Passenger passenger);
//    查询，根据乘客的id查一条
     Passenger selectById(Integer id);
//    查询所有
     List<Passenger> selectAll();




}
