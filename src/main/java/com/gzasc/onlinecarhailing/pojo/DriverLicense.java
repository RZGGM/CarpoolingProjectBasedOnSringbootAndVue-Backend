package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

import java.util.Date;

//驾驶证
@Data
public class DriverLicense {

//    在数据库里的唯一标识ID
    private Integer id;
    //    机动车驾驶证号
    private String licenseId;
    //    初次领取驾驶证日期
    private Date getDriverLicenseDate;
    //    驾驶证有效期限起
    private Date driverLicenseOn;
    //    驾驶证有效期限止
    private Date driverLicenseOff;

}
