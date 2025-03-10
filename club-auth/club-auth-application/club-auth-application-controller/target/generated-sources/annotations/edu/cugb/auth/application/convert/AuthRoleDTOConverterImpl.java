package edu.cugb.auth.application.convert;

import edu.cugb.auth.application.dto.AuthRoleDTO;
import edu.cugb.auth.domain.entity.AuthRoleBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-08T18:30:34+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class AuthRoleDTOConverterImpl implements AuthRoleDTOConverter {

    @Override
    public AuthRoleBO convertDTOToBO(AuthRoleDTO authRoleDTO) {
        if ( authRoleDTO == null ) {
            return null;
        }

        AuthRoleBO authRoleBO = new AuthRoleBO();

        return authRoleBO;
    }
}
