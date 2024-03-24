package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Passenger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//操作乘客的数据库
@Mapper
public interface PassengerMapper {

//    增加
    public Integer insert(Passenger passenger);
//    删除，通过id删除，多个
    public Integer deleteByIds(List<Integer> ids);
//    删除一个
    public Integer deleteById(Integer id);
//    修改
    public Integer update(Passenger passenger);
//    查询，根据乘客的id查一条
    public Passenger selectById(Integer id);
//    查询所有
    public List<Passenger> selectAll();

}
