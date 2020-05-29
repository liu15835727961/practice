package com.xyy.movies.service.feign;

import com.xyy.movies.service.rollback.UserFeignRollBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 声明需要调用的微服务名称
 *      @FeignClient
 *          -name:微服务提供者的名称
 */
@FeignClient(name = "eureka-client-user",fallback = UserFeignRollBack.class)
public interface UserFeignClient {
    /**
     * 配置需要调用的微服务接口
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String usertest();
}
