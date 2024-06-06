package org.example;

import com.alibaba.nacos.api.annotation.NacosProperties;;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8847"))
@NacosPropertySource(dataId = "employee",autoRefreshed = true)

@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8847"))
public class NacosConfiguration {
}
