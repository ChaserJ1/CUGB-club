package edu.cugb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author pengjia
 * @Data 2024/10/13 11:07
 * @Description: 网关启动器
 */
@SpringBootApplication
@ComponentScan("edu.cugb")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}
