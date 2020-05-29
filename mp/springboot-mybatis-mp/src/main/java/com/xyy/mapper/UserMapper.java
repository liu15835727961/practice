package com.xyy.mapper;

import com.xyy.entity.User;

public interface UserMapper extends MyBaseMapper<User> {
    User findById(Long id);
}
