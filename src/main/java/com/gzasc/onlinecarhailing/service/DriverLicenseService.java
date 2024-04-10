package com.gzasc.onlinecarhailing.service;

//对驾驶证进行操作的

import com.gzasc.onlinecarhailing.pojo.DriverLicense;

public interface DriverLicenseService {

//    根据id查询驾驶证
    DriverLicense searchDriverLicenseById(Integer id);

}
