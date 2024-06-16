package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApp {
    @Value("${spring.application.name}")
    String test;
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class , args);
    }

    @PostConstruct
    public void test(){
        System.out.println(test);
    }
}