package com.xyy;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.xyy.entity.User;
import com.xyy.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatisPlus {
    @Test
    public void testFindAll() throws IOException {
        String config="mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = userMapper.selectList(null);
        for (User u:all) {
            System.out.println(u);
        }
    }
}
