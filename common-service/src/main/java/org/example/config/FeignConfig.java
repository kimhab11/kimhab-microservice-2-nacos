package org.example.config;


import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .client(new OkHttpClient());
//                .encoder(new JacksonEncoder())
//                .decoder(new JacksonDecoder());
    }
}
