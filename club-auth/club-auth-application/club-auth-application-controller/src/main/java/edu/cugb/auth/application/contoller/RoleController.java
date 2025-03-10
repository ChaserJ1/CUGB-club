package edu.cugb.auth.application.contoller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import edu.cugb.auth.application.convert.AuthRoleDTOConverter;
import edu.cugb.auth.application.dto.AuthRoleDTO;
import edu.cugb.auth.entity.Result;
import edu.cugb.auth.domain.entity.AuthRoleBO;
import edu.cugb.auth.domain.service.AuthRoleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Resource
    private AuthRoleDomainService authRoleDomainService;


    /**
     * 新增角色接口
     *
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色key不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.add(authRoleBO));
        } catch (Exception e) {
            log.info("RoleController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色失败");
        }
    }

    /**
     * 更新角色接口
     *
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色key不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));
        } catch (Exception e) {
            log.info("RoleController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新角色失败");
        }
    }

    /**
     * 删除角色接口
     *
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色key不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        } catch (Exception e) {
            log.info("RoleController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除角色失败");
        }
    }
}


