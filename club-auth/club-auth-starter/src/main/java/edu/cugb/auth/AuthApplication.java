package edu.cugb.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author pengjia
 * @Data 2024/10/13 11:07
 * @Description: 鉴权微服务启动类
 */
@SpringBootApplication
@ComponentScan("edu.cugb")
@MapperScan("edu.cugb.**.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
