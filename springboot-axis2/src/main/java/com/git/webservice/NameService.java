package com.git.webservice;

import org.springframework.stereotype.Component;

/**
 * Webservice接口方法
 *
 * @author yulj
 * @create: 2019/03/12 19:50
 */
@Component
public class NameService {

    public String getName(String name) {
        return "你的名字是"+name;
    }
}