package edu.cugb.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import edu.cugb.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/11/6 9:54
 * @Description:
 */
@RestController
public class FileController {
    @Resource
    private FileService fileService;
    @NacosValue(value = "${storage.service.type}", autoRefreshed = true)
    private String storageType;

    @RequestMapping("/listAllBuckets")
    public String getAllBuckets() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/nacos")
    public String testNacos() throws Exception {
        return storageType;
    }


}
