package com.gruposv.microservice_adm_and_config.modules.system_users.mapper;

import com.gruposv.microservice_adm_and_config.modules.system_users.dto.RoleDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;

public class RoleDTOMapper {

    public static RoleDTO toDTO(RoleEntity roleEntity){
        return new RoleDTO(
                roleEntity.getId(),
                roleEntity.getRoleName(),
                roleEntity.getDescription(),
                roleEntity.getPermissions()
                        .stream()
                        .map(permission -> permission.getPermissionKey())
                        .toList()
        );
    }

    public static RoleEntity toEntity(RoleDTO dto) {
        RoleEntity role = new RoleEntity();
        role.setId(dto.id());
        role.setRoleName(dto.roleName());
        role.setDescription(dto.description());
        return role;
    }

}
