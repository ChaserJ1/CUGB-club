package edu.cugb.auth.application.convert;

import edu.cugb.auth.application.dto.AuthRolePermissionDTO;
import edu.cugb.auth.domain.entity.AuthRolePermissionBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T16:46:07+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class AuthRolePermissionDTOConverterImpl implements AuthRolePermissionDTOConverter {

    @Override
    public AuthRolePermissionBO convertDTOToBO(AuthRolePermissionDTO authRolePermissionDTO) {
        if ( authRolePermissionDTO == null ) {
            return null;
        }

        AuthRolePermissionBO authRolePermissionBO = new AuthRolePermissionBO();

        authRolePermissionBO.setId( authRolePermissionDTO.getId() );
        authRolePermissionBO.setRoleId( authRolePermissionDTO.getRoleId() );
        authRolePermissionBO.setPermissionId( authRolePermissionDTO.getPermissionId() );
        List<Long> list = authRolePermissionDTO.getPermissionIdList();
        if ( list != null ) {
            authRolePermissionBO.setPermissionIdList( new ArrayList<Long>( list ) );
        }

        return authRolePermissionBO;
    }
}
