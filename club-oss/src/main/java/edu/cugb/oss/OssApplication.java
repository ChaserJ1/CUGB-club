package edu.cugb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author pengjia
 * @Data 2024/10/13 11:07
 * @Description: oss服务启动器
 */
@SpringBootApplication
@ComponentScan("edu.cugb")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
