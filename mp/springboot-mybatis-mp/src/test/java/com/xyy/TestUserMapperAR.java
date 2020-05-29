package com.xyy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xyy.entity.User;
import com.xyy.enums.SexEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserMapperAR {
    @Test
    public void testSelectById(){
        User user=new User();
        user.setId(21L);
        User user1 = user.selectById();
        System.out.println(user1);
    }
    @Test
    public void testAddUser(){
        User user=new User();
        user.setUserName("daiochan");
        user.setPassword("456789");
        user.setName("貂蝉");
        user.setAge(23);
        user.setEmail("daiochan.@qq.com");
        user.setVersion(1);
        user.setSex(SexEnum.WOMAN);
        //调用AR的insert方法
        boolean insert = user.insert();
        System.out.println("数据库是否添加成功:"+insert);
    }

    /**
     * 特别说明:
     *
     * 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 整数类型下 newVersion = oldVersion + 1
     * newVersion 会回写到 entity 中
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     */
    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(2L);
        user.setAge(39);
        user.setVersion(1);//当前版本信息
        boolean b = user.updateById();
        System.out.println("数据库修改结果:"+b);
    }

    /**
     * 测试全表更新,SQL分析器的阻断效果
     */
    @Test
    public void testUpdateAll(){
        User user=new User();
        user.setAge(22);
        boolean b = user.update(null);
        System.out.println("数据库修改结果:"+b);
    }
    @Test
    public void testDelete(){
        User user=new User();
        user.setId(22L);
        boolean b = user.deleteById();
        System.out.println("数据库删除结果:"+b);
    }
    @Test
    public void testSelect(){
        User user=new User();
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.in("id",12L,13L,14L);
        user.selectList(qw);
    }
}
