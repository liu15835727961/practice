package com.xyy.movies.controller;

import com.netflix.appinfo.InstanceInfo;
import com.xyy.movies.service.MoviesService;
import com.xyy.movies.service.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 注入DiscoveryClient
     *  SpringCloud提供的获取元数据的工具类
     *      调用方法获取服务的元数据
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private UserFeignClient userFeignClient;

    @RequestMapping("/")
    public void index(){
        List<ServiceInstance> instancesById = discoveryClient.getInstances("eureka-client-user");
        for (ServiceInstance instanceInfo : instancesById) {
            System.out.println(instanceInfo.toString());
        }
    }
    @RequestMapping("/test")
    private String movietest(){
        String usertest = userFeignClient.usertest();
        return usertest;
    }
}
