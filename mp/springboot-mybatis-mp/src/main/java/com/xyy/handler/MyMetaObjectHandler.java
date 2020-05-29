package com.xyy.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取到password的值,再进行判断如果为空,进行填充,不为空不做处理
        Object password = getFieldValByName("password", metaObject);
        if (password==null && password.toString().equals("")){
            setFieldValByName("password","888888",metaObject);
        }
    }
    /**
     * 更新数据时填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
