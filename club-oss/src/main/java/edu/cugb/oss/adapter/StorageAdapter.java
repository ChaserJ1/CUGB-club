package edu.cugb.oss.adapter;

import edu.cugb.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


/**
 * @Author pengjia
 * @Data 2024/11/6 10:34
 * @Description: 文件存储适配器
 */
public interface StorageAdapter {


    /**
     * 创建一个bucket桶
     */
    void creatBucket(String bucket);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 列出所有桶名
     */
    List<String> getAllBucket();

    /**
     * 列出当前桶名及文件
     */
    List<FileInfo> getAllFile(String bucket);

    /**
     * 下载文件
     */
    InputStream downLoad(String bucket, String objectName);

    /**
     * 删除桶
     */
    void deleteBucket(String bucket);

    /**
     * 删除文件
     */
    void deleteObject(String bucket, String objectName);

    /**
     * 获取文件路径
     */
    String getUrl(String bucket, String objectName);
}
