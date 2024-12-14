package edu.cugb.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import edu.cugb.oss.service.FileService;
import edu.cugb.oss.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 列出所有桶
     */
    @RequestMapping("/listAllBuckets")
    public String getAllBuckets() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    /**
     *  查询url
     */
    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        String url = fileService.uploadFile(uploadFile, bucket, objectName);
        return Result.ok(url);
    }


}
