package com.zk;

import com.zk.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author CoderZk
 */
@Configuration
public class RoutesConfiguration {

    @Autowired
    private KeyResolver hostNameResolver;

    @Autowired
    @Qualifier("redisLimiterUser")
    private RateLimiter rateLimiterUser;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder.routes()
                // Auth 在网关层有很多种做法, 我用了种最丑的, 可以试试其他做法
                // 1. [最常用] 网关层或微服务自己本地校验jwt token的有效性, 不向auth-service发起远程调用
                // 2. [路由配置最简单] 可以把 AuthFilter 注册为 global filter,
                //    然后在 AuthFilter 里配置需要过滤的 url pattern (也可以从 config-server 动态拉取)
                // 3. [路由配置最简单] 也可以采用 interceptor 的形式
                // 4. [路由配置最丑] 只是为了演示下自定义过滤器
                // 要注意声明 URL Pattern 的先后顺序, 一个URL可能匹配多个路由, 先来后到

                // FIXME 修改前端JS代码, 记得把login后返回的jwt-token和refresh-token带到每个请求的header里面
                .route(r -> r.path("/address/list",
                                "/address/add",
                                "/address/update",
                                "/address/setDefault",
                                "/address/delete",
                                "userInfo/**",
                                "center/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://FOODIE-USER-SERVICE")
                )
                .route(r -> r.path("/auth-service/refresh")
                        .uri("lb://FOODIE-AUTH-SERVICE")
                )
                // FIXME search 服务还没写
                .route(r -> r.path("/search/**", "/index/**", "/items/search", "/items/catItems")
                        .uri("lb://FOODIE-SEARCH-SERVICE")
                )
                // 配置url pattern经常会漏掉某些字符导致转发错误
                .route(r -> r.path("/address/**", "/passport/**", "/userInfo/**", "/center/**")
                        .filters(f -> f.requestRateLimiter(config -> {
                            config.setKeyResolver(hostNameResolver);
                            config.setRateLimiter(rateLimiterUser);
                            config.setStatusCode(HttpStatus.BAD_GATEWAY);
                        }))
                        .uri("lb://FOODIE-USER-SERVICE")
                )
                .route(r -> r.path("/items/**")
                        .uri("lb://FOODIE-ITEM-SERVICE")
                )
                .route(r -> r.path("/orders/**", "/myorders/**", "/mycomments/**")
                        .uri("lb://FOODIE-ORDER-SERVICE")
                )
                .build();
    }

}
