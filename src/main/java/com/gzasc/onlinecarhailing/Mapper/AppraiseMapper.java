package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.Appraise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对评价表的操纵
@Mapper
public interface AppraiseMapper {


//    增加一条评价
    Integer insertAppraise(Appraise appraise);
//    删除评价，批量和删一条是一样的
    Integer deleteAppraiseByIds(List<Integer> ids);
//    修改评价
    Integer updateAppraise(Appraise appraise);
//    根据司机的id查询所有评价
    List<Appraise> selectAppraiseByDriverId(Integer driverId);

}
