package edu.cugb.subject.application.interceptor;

import edu.cugb.subject.application.context.LoginContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author: pengjia
 * @Description: Feign请求拦截器
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();//提供对HTTP请求数据的访问。它封装了客户端请求的所有信息，包括请求头、请求参数、请求方法（GET、POST等）、请求URL、会话信息以及输入流等。
        if (Objects.nonNull(request)){
            String header = request.getHeader("loginId");
            if (StringUtils.isNotBlank(header)){
                requestTemplate.header("loginId",header);
            }
        }


    }
}
