package com.xyy.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.xyy.injectors.MySqlInjector;
import com.xyy.plugins.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MybatisPlusConfig {

    /*@Bean//配置分页插件
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }*/
    @Bean //oracle的序列生成器
    public OracleKeyGenerator oracleKeyGenerator(){
        return new OracleKeyGenerator();
    }
    @Bean //自定义拦截器
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }
    //@Bean //sql语句分析插件
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser> list=new ArrayList<>();
        list.add(new BlockAttackSqlParser());//全表更新或删除的阻断器
        sqlExplainInterceptor.setSqlParserList(list);
        return sqlExplainInterceptor;
    }
    @Bean//乐观锁的插件
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 注入自定义的SQL注入器
     * @return
     */
    @Bean
    public MySqlInjector mySqlInjector(){
        return new MySqlInjector();
    }
}
