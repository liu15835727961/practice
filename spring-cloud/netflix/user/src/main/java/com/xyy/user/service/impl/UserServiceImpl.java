package com.xyy.user.service.impl;

import com.xyy.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String test() {
        return "user test";
    }
}
