package edu.cugb.auth.application.contoller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import edu.cugb.auth.application.convert.AuthRolePermissionDTOConverter;
import edu.cugb.auth.application.dto.AuthRolePermissionDTO;
import edu.cugb.auth.entity.Result;
import edu.cugb.auth.domain.entity.AuthRolePermissionBO;
import edu.cugb.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限关联controller
 */
@RestController
@RequestMapping("/rolePermission")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增权限
     */
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "角色拥有的权限列表不能为空");
            AuthRolePermissionBO rolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(rolePermissionBO));
        } catch (Exception e) {
            log.error("RolePermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色权限失败");
        }
    }

}
