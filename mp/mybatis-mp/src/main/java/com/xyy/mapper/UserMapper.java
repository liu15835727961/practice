package com.xyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xyy.entity.User;

import java.util.List;

/**
 * 继承mybatis-plus中的BaseMapper接口
 *      可以使用BaseMapper中的自定义的方法进行CRUD
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 该方法是mybatis的方法,需要自己写实现xml或注解
     * @return
     */
    List<User> findAll();
}
