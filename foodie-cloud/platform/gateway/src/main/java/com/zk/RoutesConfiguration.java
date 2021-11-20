package com.zk;

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
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // FIXME search 服务还没写
                .route(r -> r.path("/search/**", "/index/**", "/items/search", "/items/catItems")
                        .uri("lb://FOODIE-SEARCH-SERVICE")
                )
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
