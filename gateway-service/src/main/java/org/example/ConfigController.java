package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("config")
public class ConfigController {

    @Value("${name:false}")
    private String name;

    @RequestMapping("/get")
    public String get() {
        return name;
    }
}
