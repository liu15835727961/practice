package com.xyy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xyy.entity.User;
import com.xyy.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperPlusTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 使用mybatis-plus提供的方法进行插入
     */
    @Test
    public  void testInsert(){
        User user=new User();
        user.setAge(30);
        user.setName("曹操");
        user.setUserName("caocao");
        user.setEmail("test5@email.cm");
        user.setPassword("123456");
        int insert = userMapper.insert(user);
        System.out.println("数据库受影响的行数:"+insert);
        //获取自增长的id
        System.out.println("id=>"+user.getId());
    }
    /**
     * 使用mybatis-plus提供的方法通过id修改数据
     */
    @Test
    public void testUpdatebyId(){
        User user=new User();
        user.setId(1L);
        user.setUserName("zhangsan1");
        int i = userMapper.updateById(user);
        System.out.println("result=>"+i);
    }
    /**
     * 使用mybatis-plus提供的方法通过条件修改数据
     */
    @Test
    public void testUpdate(){
        User user=new User();
        user.setPassword("666666");
        QueryWrapper<User>  wrapper=new QueryWrapper();
        wrapper.eq("user_name","zhangsan1");//设置条件,通过用户名更新,user_name代表数据库中的字段
        int i =userMapper.update(user,wrapper);
        System.out.println("result=>"+i);
    }
    /**
     * 使用mybatis-plus提供的方法通过条件修改数据
     */
    @Test
    public void testUpdate2(){
        UpdateWrapper<User> wrapper=new UpdateWrapper<>();
        wrapper.set("age",22).set("password","999999")//更新的字段
                .eq("user_name","zhangsan1");//设置条件,通过用户名更新,user_name代表数据库中的字段
        int update = userMapper.update(null, wrapper);
        System.out.println("result=>"+update);
    }
    /**
     * 使用mybatis-plus提供的方法通过id删除数据
     */
    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(5L);
        System.out.println("result=>"+i);
    }
    /**
     * 使用mybatis-plus提供的方法通过条件进行删除
     */
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("user_name","zhaoliu");
        map.put("password","123456");
        //根据map删除数据,多条件之间是and的关系
        int i = userMapper.deleteByMap(map);
        System.out.println("result=>"+i);
    }
    /**
     * 使用mybatis-plus提供的方法通过条件进行删除
     */
    @Test
    public void testDelete(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name","zhangsan1").eq("password","999999");
        User user=new User();
        user.setUserName("lisi");
        user.setPassword("123456");
        QueryWrapper<User> wrapper2=new QueryWrapper<>(user);
        int i = userMapper.delete(wrapper2);
        System.out.println("result=>"+i);
    }
    /**
     * 使用mybatis-plus提供的方法通过集合id进行批量删除
     */
    @Test
    public void testDeleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(20L,21L));
        System.out.println("result=>"+i);
    }
    /**
     * 使用mybatis-plus提供的方法通过id查找数据
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
    /**
     * 使用mybatis-plus提供的方法通过id集合进行批量查询
     */
    @Test
    public void testSelectBatchIds(){
       List<User>  list = userMapper.selectBatchIds(Arrays.asList(10L,11L,12L));
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 使用mybatis-plus提供的方法进行查询一条数据
     */
    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("user_name","zhangsan").eq("id",6L);
        //根据条件查询数据,如果大于一条会报错
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    /**
     * 使用mybatis-plus提供的方法进行数据条数
     */
    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("user_name","zhangsan");
        //根据条件查询数据,如果大于一条会报错
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println(integer);
    }
    /**
     * 使用mybatis-plus提供的方法通过条件返回User集合
     */
    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("user_name","zhangsan");
        //根据条件查询数据,如果大于一条会报错
        List<User> list = userMapper.selectList(wrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 使用mybatis-plus提供的方法进行分页查询
     */
    @Test
    public void testSelectPage(){
        Page<User> page=new Page<>(1,2);//查询第一页,查询2条数据
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("user_name","zhangsan");
        //根据条件查询数据,如果大于一条会报错
        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数:"+iPage.getTotal());
        System.out.println("数据的总页数:"+iPage.getPages());
        System.out.println("数据的当前页数:"+iPage.getCurrent());
        System.out.println("数据查询条数:"+iPage.getSize());
        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }

    }
    /**
     * sql注入的原理
     *      在Mp中,ISqlInjector负责sql注入工作,他是一个接口,AbstractSqlInjector是它的实现类
     *        查看类的快捷键:CTRL+N
     *        查找接口实现类的快捷键:ctrl + alt +B
     *        查看类或接口的继承关系：ctrl + h
     */
    /**
     * 调用自定义的FindById方法查询数据
     */
    @Test
    public void testFindById(){
        User user = userMapper.findById(18L);
        System.out.println(user);
    }
    /**
     * 条件构造器
     *      allEq
     */
    @Test
    public void testAllEq(){
        Map<String,Object> map=new HashMap<>();
        map.put("user_name","lisi");
        map.put("name",null);
        map.put("age",20);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //SELECT id,user_name,name,age,email FROM tb_user WHERE (user_name = ? AND name IS NULL AND age = ?)
        //wrapper.allEq(map);
        //SELECT id,user_name,name,age,email FROM tb_user WHERE (user_name = ? AND age = ?)
        //wrapper.allEq(map,false);
        //SELECT id,user_name,name,age,email FROM tb_user WHERE (age = ?)
        wrapper.allEq((k,v)->(k.equals("age")||k.equals("id")),map);
        List<User> list = userMapper.selectList(wrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 基本比较操作
     */
    @Test
    public void testEq(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //SELECT id,user_name,name,age,email FROM tb_user WHERE (password = ? AND age >= ? AND name IN (?,?,?))
        wrapper.eq("password","123456")//密码=123456
                .ge("age",20)//年龄>=20
                .in("name","李四","王五","张三");//名字在张三,李四,王五之中
        List<User> list = userMapper.selectList(wrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 模糊查询
     */
    @Test
    public void testLike(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //SELECT id,user_name,name,age,email FROM tb_user WHERE (name LIKE ?)  参数:%张%
        //wrapper.like("name","张");
        //SELECT id,user_name,name,age,email FROM tb_user WHERE (name LIKE ?) 参数: %三
        wrapper.likeLeft("name","三");
        List<User> list = userMapper.selectList(wrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 按照年龄进行降序排序
     */
    @Test
    public void testOrderByAgeDesc(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //SELECT id,user_name,name,age,email FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");
        List<User> list = userMapper.selectList(wrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 逻辑查询
     */
    @Test
    public void testOr(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //SELECT id,user_name,name,age,email FROM tb_user WHERE (name = ? OR age = ?)
        wrapper.eq("name","王五").or().eq("age",18);
        List<User> list = userMapper.selectList(wrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    /**
     * 设置查询字段
     */
    @Test
    public void testSelect(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //SELECT name,age FROM tb_user 
        wrapper.select("name","age");
        List<User> list = userMapper.selectList(wrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void  testMyMethodFindAllkey(){
        List<User> list = userMapper.findAllKey();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
