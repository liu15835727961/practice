package com.xyy.movies.service.rollback;

import com.xyy.movies.service.feign.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignRollBack implements UserFeignClient {
    @Override
    public String usertest() {
        return "movies fallback";
    }
}
