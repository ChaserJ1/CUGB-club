package edu.cugb.auth.api;

import edu.cugb.auth.entity.AuthUserDTO;
import edu.cugb.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: pengjia
 * @Date: 2025/1/6 19:16
 * @Description:  用户服务feign
 */
@FeignClient("club-auth")
public interface UserFeignService {

    @RequestMapping("/user/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);
}
