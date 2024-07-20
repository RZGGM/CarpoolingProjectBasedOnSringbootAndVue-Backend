package com.gzasc.onlinecarhailing.service.impl;

import com.gzasc.onlinecarhailing.service.NovelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovelServiceImpl implements NovelServiceInterface {

    @Autowired
    private NovelMapper novelMapper;
    @Override
    public Integer addOne(Novel object) {
        return novelMapper.insertOne(object);
    }
}
