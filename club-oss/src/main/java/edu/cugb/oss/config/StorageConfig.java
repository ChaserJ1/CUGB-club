package edu.cugb.oss.config;

import edu.cugb.oss.adapter.StorageAdapter;
import edu.cugb.oss.adapter.AliStorageAdapter;
import edu.cugb.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author pengjia
 * @Data 2024/11/7 21:46
 * @Description:
 */
@Configuration
@RefreshScope
public class StorageConfig {
    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    @RefreshScope
    public StorageAdapter storageService() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else
            throw new IllegalArgumentException("未找到对应的文件存储处理器");
    }
}
