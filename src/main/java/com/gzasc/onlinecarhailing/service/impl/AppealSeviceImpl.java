package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.Mapper.AppealMapper;
import com.gzasc.onlinecarhailing.service.AppealSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealSeviceImpl implements AppealSevice {

    @Autowired
    AppealMapper appealMapper;

    @Override
    public Integer addAppeal(Appeal appeal) {
        return appealMapper.insertAppeal(appeal);
    }

    @Override
    public Integer removeAppeal(Integer appealId) {
        return appealMapper.deleteAppeal(appealId);
    }

    @Override
    public Integer modAppeal(Appeal appeal) {
        return appealMapper.updateAppeal(appeal);
    }

    @Override
    public Appeal searchAppealByAppealId(Integer appealId) {
        return appealMapper.selectAppealByAppealId(appealId);
    }

    @Override
    public Appeal searchAppealByOrderId(Integer orderId) {
        return appealMapper.selectAppealByOrderId(orderId);
    }

    @Override
    public List<Appeal> searchAppealsByUserId(Integer userId) {
        return appealMapper.selectAppealByUserId(userId);
    }
}
