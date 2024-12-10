package edu.cugb.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author pengjia
 * @Data 2024/10/13 11:07
 * @Description: 微信微服务启动类
 */
@SpringBootApplication
@ComponentScan("edu.cugb")
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class);
    }
}
