package edu.cugb.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Author pengjia
 * @Data 2024/12/23 16:17
 * @Description: 线程池管理
 * 自定义线程池的好处：
 * 1、灵活
 * 2、可以指定队列，不会造成堆栈溢出
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "labelThreadPool")
    public ThreadPoolExecutor getLabelThreadPool() {
        return new ThreadPoolExecutor(20, 100, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(40),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
