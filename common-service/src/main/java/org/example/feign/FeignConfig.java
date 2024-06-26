package org.example.feign;


import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public RequestInterceptor resilience4jRequestInterceptor(CircuitBreakerRegistry circuitBreakerRegistry, RetryRegistry retryRegistry) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("catalogService");
        Retry retry = retryRegistry.retry("catalogService");

        return template -> {
            template.header("CB", circuitBreaker.getState().name());
            template.header("Retry", retry.toString());
        };
    }
}
