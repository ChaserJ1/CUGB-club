package edu.cugb.subject.infra.rpc;


import edu.cugb.auth.api.UserFeignService;
import edu.cugb.auth.entity.AuthUserDTO;
import edu.cugb.auth.entity.Result;
import edu.cugb.subject.infra.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: pengjia
 * @Description:
 */
@Component
public class UserRPC {
    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String userName){
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(userName);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if (!result.getSuccess()){
            return userInfo;
        }

        AuthUserDTO data = result.getData();
        userInfo.setUserName(data.getUserName());
        userInfo.setNickName(data.getNickName());
        return userInfo;

    }
}
