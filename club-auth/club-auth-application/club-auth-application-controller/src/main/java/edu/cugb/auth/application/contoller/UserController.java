package edu.cugb.auth.application.contoller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import edu.cugb.auth.application.convert.AuthUserDTOConverter;
import edu.cugb.auth.application.dto.AuthUserDTO;
import edu.cugb.auth.common.entity.Result;
import edu.cugb.auth.domain.entity.AuthUserBO;
import edu.cugb.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 用户注册接口
     *
     * @param authUserDTO
     * @return
     */
    @RequestMapping("/register")
    public Result register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            //日志输出
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            //参数校验
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮箱不能为空");

            //DTO转BO, 将数据转换成BO, 交给domain层做下一步处理
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            //调domainService做具体实现
            authUserDomainService.register(authUserBO);
            return Result.ok(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.error:{}", e.getMessage(), e);
            return Result.fail("注册失败");

        }


    }














    // 登录接口
    @RequestMapping("/doLogin")
    public SaResult doLogin(String username, String password) {
        if ("zhang".equals(username) && "123456".equals(password)) {
            // 第1步，先登录上
            StpUtil.login(10001);
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 第3步，返回给前端
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态，浏览器访问： http://localhost:3011/user/isLogin
    @PostMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
