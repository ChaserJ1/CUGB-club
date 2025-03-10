package edu.cugb.auth.application.convert;

import edu.cugb.auth.application.dto.AuthRoleDTO;
import edu.cugb.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色dto转换器
 */
@Mapper
public interface AuthRoleDTOConverter {

    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    AuthRoleBO convertDTOToBO(AuthRoleDTO authRoleDTO);

}
