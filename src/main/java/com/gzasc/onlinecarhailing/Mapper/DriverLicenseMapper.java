package com.gzasc.onlinecarhailing.Mapper;

import com.gzasc.onlinecarhailing.pojo.DriverLicense;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DriverLicenseMapper {

//    根据驾驶证的id查询驾驶证
    DriverLicense selectByDriverLicenseId(Integer driverLicenseId);

}
