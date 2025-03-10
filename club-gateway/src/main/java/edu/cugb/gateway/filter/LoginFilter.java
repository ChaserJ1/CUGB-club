package edu.cugb.gateway.filter;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author pengjia
 * @Data 2024/12/25 19:29
 * @Description:  登录拦截器
 */
@Component
@Slf4j
public class LoginFilter implements GlobalFilter {
    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        if (log.isInfoEnabled()){
            log.info("LoginFilter.filter.url:{}",url);
        }
        if (url.equals("/auth/user/doLogin")){
            chain.filter(exchange);
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String loginId = (String) tokenInfo.getLoginId();
        if (StringUtils.isBlank(loginId)){
            throw new Exception("未获取到用户信息！");
        }

        //将loginId放入header中
        mutate.header("loginId",loginId);
        /*
        mutate.build() 构建了一个新的 ServerHttpRequest 实例，该实例包含了之前通过
        mutate.header("loginId", loginId) 添加的头部信息
        exchange.mutate().request(): 使用这个新构建的请求替换原始请求。
        最终调用 build() 方法创建一个新的 ServerWebExchange 实例
        */
        return chain.filter(exchange.mutate().request(mutate.build()).build());

    }
}
