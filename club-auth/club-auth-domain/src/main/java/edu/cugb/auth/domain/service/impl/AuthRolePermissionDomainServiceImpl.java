package edu.cugb.auth.domain.service.impl;

import edu.cugb.auth.common.enums.IsDeletedFlagEnum;
import edu.cugb.auth.domain.entity.AuthRolePermissionBO;
import edu.cugb.auth.domain.service.AuthRolePermissionDomainService;
import edu.cugb.auth.infra.basic.entity.AuthRolePermission;
import edu.cugb.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        LinkedList<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId ->
                {
                    AuthRolePermission authRolePermission = new AuthRolePermission();
                    authRolePermission.setPermissionId(permissionId);
                    authRolePermission.setRoleId(roleId);
                    authRolePermission.setIsDeleted(IsDeletedFlagEnum.Un_DELETED.getCode());
                    rolePermissionList.add(authRolePermission);
                }
        );
        int count = authRolePermissionService.batchInsert(rolePermissionList);
        return count > 0;
    }


}
