package edu.cugb.auth.domain.service;

import edu.cugb.auth.domain.entity.AuthRoleBO;

/**
 * 角色领域service
 *
 * @author: ChickenWing
 * @date: 2023/11/1
 */
public interface AuthRoleDomainService {

    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);

}
