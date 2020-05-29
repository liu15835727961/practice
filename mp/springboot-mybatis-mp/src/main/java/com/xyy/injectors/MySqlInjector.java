package com.xyy.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.ArrayList;
import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> list=new ArrayList<>();
        //获取父类集合
        list.addAll(super.getMethodList());
        //扩充自定义的方法
        list.add(new FinaAllKey());
        return list;
    }
}
