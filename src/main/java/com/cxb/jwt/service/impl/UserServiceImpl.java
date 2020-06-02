package com.cxb.jwt.service.impl;

import com.cxb.jwt.entity.User;
import com.cxb.jwt.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public boolean checkUser(String loginName, String passWord) {
        return true;
    }

    @Override
    public User getUser(String loginName) {
        User user = new User();
        user.setName("小石潭记");
        user.setPassword("123");
        return user;
    }
}

