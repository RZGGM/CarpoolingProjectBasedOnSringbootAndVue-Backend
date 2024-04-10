package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.DriverLicenseMapper;
import com.gzasc.onlinecarhailing.pojo.DriverLicense;
import com.gzasc.onlinecarhailing.service.DriverLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverLicenseServiceImpl implements DriverLicenseService {

    @Autowired
    DriverLicenseMapper driverLicenseMapper;

    @Override
    public DriverLicense searchDriverLicenseById(Integer id) {
        return driverLicenseMapper.selectByDriverLicenseId(id);
    }
}
