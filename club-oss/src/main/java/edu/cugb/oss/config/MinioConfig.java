package edu.cugb.oss.config;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author pengjia
 * @Data 2024/11/4 21:43
 * @Description: Minio的配置管理
 */
@Configuration
public class MinioConfig {
    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;

    /**
     * minio帐户
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 构造minioClient
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
