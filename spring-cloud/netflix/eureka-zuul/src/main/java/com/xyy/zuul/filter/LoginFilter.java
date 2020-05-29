package com.xyy.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * 定义一个登陆过滤器
 */
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 定义过滤器类型
     *  pre 转发到微服务之前执行的过滤器
     *  routing 在路由请求时执行的过滤器
     *  post 执行微服务获取返回值之后执行的过滤器
     *  error 在整个阶段抛出异常执行的过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 指定过滤器的执行顺序
     *      返回值越小,执行顺序越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否生效
     *      true:使用此过滤器
     *      false:不使用过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行过滤器的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("执行了过滤器");
        return null;
    }
}
