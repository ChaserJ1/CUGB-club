package edu.cugb.subject.application.config;

import edu.cugb.subject.application.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.encoding.FeignAcceptGzipEncodingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pengjia
 * @Description:
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }
}
