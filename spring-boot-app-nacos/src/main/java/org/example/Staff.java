package org.example;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "staff" , autoRefreshed = true)
public class Staff {
    public static void main(String[] args) {
        SpringApplication.run(Staff.class,args);
    }
}