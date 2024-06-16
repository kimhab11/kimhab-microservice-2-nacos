//package org.example;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//
//public class Config {
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
//        return routeLocatorBuilder.routes()
//                .route(r ->
//                        r
//                                .path("/user/**")
//                             //   .uri("lb://user-service")
//                                .uri("http://localhost:1112")
//                )
//                .build();
//    }
//}
