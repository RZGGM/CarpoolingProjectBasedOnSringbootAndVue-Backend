package com.gzasc.onlinecarhailing.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//对投诉表的操作
public interface AppealMapper {

    //    增加投诉
    Integer insertAppeal(Appeal appeal);

    //    删除一条投诉
    Integer deleteAppeal(Integer appealId);

    //    修改投诉的内容
    Integer updateAppeal(Appeal appeal);

    //    通过id查询一条投诉
    Appeal selectAppealByAppealId(Integer id);

    //    通过订单的id查询一条投诉
    Appeal selectAppealByOrderId(Integer id);

    //    通过用户的id查询它的投诉
    List<Appeal> selectAppealByUserId(Integer id);

}
