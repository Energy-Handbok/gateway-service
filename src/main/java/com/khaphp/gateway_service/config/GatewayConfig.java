//package com.khaphp.gateway_service.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("user-service", x -> x
//                        .path(
//                                "/api/v1/user/**",
//                                "/auth/**",
//                                "/api/v1/user-system/**"
//                        )
//                        .uri("lb://user-service")
//                )
//                .route("payment-service", x -> x
//                        .path(
//                                "/api/v1/wallet/**",
//                                "/api/v1/wallet-transaction/**"
//                        )
//                        .uri("lb://payment-service")
//                )
//                .route("order-service", x -> x
//                        .path(
//                                "/api/v1/order/**",
//                                "/api/payment/**"
//                        )
//                        .uri("lb://order-service")
//                )
//                .route("notification-service", x -> x
//                        .path(
//                                "/api/v1/notification/**"
//                        )
//                        .uri("lb://notification-service")
//                )
//                .route("interact-service", x -> x
//                        .path(
//                                "/api/v1/comment/**",
//                                "/api/v1/interact/**"
//                        )
//                        .uri("lb://interact-service")
//                )
//                .route("food-service", x -> x
//                        .path(
//                                "/api/v1/food/**",
//                                "/api/v1/food-encyclopedia/**"
//                        )
//                        .uri("lb://food-service")
//                )
//                .route("food-recipe-service", x -> x
//                        .path(
//                                "/api/v1/cooking-recipe/**",
//                                "/api/v1/food-tutorials/**",
//                                "/api/v1/recipe-ingredients/**"
//                                )
//                        .uri("lb://food-recipe-service")
//                )
//                .route("News-service", x -> x
//                        .path("/api/v1/news/**")
//                        .uri("lb://news-service")
//                )
//                .build();
//    }
//}
