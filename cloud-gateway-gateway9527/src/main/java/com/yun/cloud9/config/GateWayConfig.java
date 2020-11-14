package com.yun.cloud9.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yun
 * @version 1.0
 * @date 2020-11-12 14:12
 */
@Configuration
public class GateWayConfig {

    /**
     * 配置一个id为route-name的路由规则
     * 当访问地址http://localhost:9527/guonei时会自动转发到http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_yun",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_yun2",
                r -> r.path("/lady")
                        .uri("http://news.baidu.com/lady")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator3(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_yun3",
                r -> r.path("/internet")
                        .uri("http://news.baidu.com/internet")).build();
        return routes.build();
    }
}
