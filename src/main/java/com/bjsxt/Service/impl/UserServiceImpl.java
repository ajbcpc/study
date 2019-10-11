package com.bjsxt.Service.impl;

import com.bjsxt.Service.UserService;
import com.bjsxt.mapper.UserMapper;
import com.bjsxt.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User login(User user) {
        return userMapper.selBuyUser(user);
    }
}
