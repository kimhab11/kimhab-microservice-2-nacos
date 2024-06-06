package org.example;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// curl -X POST "http://127.0.0.1:8847/nacos/v1/cs/configs?dataId=employee&group=DEFAULT_GROUP&content=testName=kimhabqqq"

@Controller
@RequestMapping("config")
public class ConfigController {

    @NacosInjected
    private NamingService namingService;
    @NacosValue(value = "${isLoad:false}", autoRefreshed = true)
    private boolean isLoad;

    @NacosValue(value = "${testName:default}", autoRefreshed = true)
    private String testName;

    @GetMapping( "/get/config-boolean")
    @ResponseBody
    public boolean get() {
        return isLoad;
    }

    @GetMapping( "/get/config-string")
    @ResponseBody
    public String get1() {
        return testName;
    }

    @GetMapping("get/service-name")
    @ResponseBody
    public List<Instance> getService(@RequestParam String servName) throws NacosException{
        return namingService.getAllInstances(servName);
    }
}
