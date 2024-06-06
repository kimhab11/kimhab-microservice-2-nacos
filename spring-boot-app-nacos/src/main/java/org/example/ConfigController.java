package org.example;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("config")
public class ConfigController {
    @NacosValue(value = "${name1:string}" , autoRefreshed = true)
    private String name1;

    @NacosInjected
    private NamingService namingService;

    @GetMapping("/get")
    @ResponseBody
    public String getName1(){
        return name1;
    }

    @GetMapping("/get-name")
    @ResponseBody
    public List<Instance> getServName(@RequestParam String nameSer) throws NacosException {
        namingService.getAllInstances(nameSer);
        return namingService.getAllInstances(nameSer);
    }
}
