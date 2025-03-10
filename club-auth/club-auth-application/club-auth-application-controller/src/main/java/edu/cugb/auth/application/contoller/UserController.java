package edu.cugb.auth.application.contoller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import edu.cugb.auth.application.convert.AuthUserDTOConverter;

import edu.cugb.auth.entity.Result;
import edu.cugb.auth.domain.entity.AuthUserBO;
import edu.cugb.auth.domain.service.AuthUserDomainService;
import edu.cugb.auth.entity.AuthUserDTO;
import edu.cugb.auth.infra.basic.service.AuthUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private AuthUserDomainService authUserDomainService;
    @Resource
    private AuthUserRoleService authUserRoleService;

    /**
     * 用户信息校验
     * @param authUserDTO
     */
    private void checkUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮箱不能为空");
    }

    /**
     * 用户注册接口
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
            checkUserInfo(authUserDTO);

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

    /**
     * 用户登录接口
     * @param validCode
     * @return
     */
    @RequestMapping("/doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {

            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空");
            return Result.ok(authUserDomainService.doLogin(validCode));
        } catch (Exception e) {
            log.error("UserController.doLogin.error:{}", e.getMessage(), e);
            return Result.fail("用户登录失败");
        }

    }

    /**
     * 获取用户信息接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()){
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            AuthUserDTO resultDTO = AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo);
            return Result.ok(resultDTO);

        } catch (Exception e) {
            log.error("UserController.getUserInfo.error:{}", e.getMessage(), e);
            return Result.fail("获取用户信息失败");
        }

    }

    /**
     * 修改用户信息接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            //日志输出
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            //参数校验
            checkUserInfo(authUserDTO);

            //DTO转BO, 将数据转换成BO, 交给domain层做下一步处理
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            //调domainService做具体实现
            authUserDomainService.update(authUserBO);

            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 删除用户信息接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            //日志输出
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }

            //DTO转BO, 将数据转换成BO, 交给domain层做下一步处理
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            //调domainService做具体实现
            authUserDomainService.delete(authUserBO);

            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除用户信息失败");

        }
    }

    /**
     * 更改用户状态接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("/changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            //日志输出
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }

            //参数校验
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            //DTO转BO, 将数据转换成BO, 交给domain层做下一步处理
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            //调domainService做具体实现
            authUserDomainService.update(authUserBO);

            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeStatus.error:{}", e.getMessage(), e);
            return Result.fail("更改用户状态失败");

        }


    }

    /**
     * 用户退出接口
     * @param userName
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(@RequestParam("userName") String userName) {
        try {
            //日志输出
            log.info("UserController.logout.userName:{}", userName);

            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空");
            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.error("UserController.logout.error:{}", e.getMessage(), e);
            return Result.fail("用户退出失败");
        }
    }


    // 查询登录状态，浏览器访问： http://localhost:3011/user/isLogin
    @PostMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
