package com.xyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MyBaseMapper<T>  extends BaseMapper<T> {
    List<T> findAllKey();
}
