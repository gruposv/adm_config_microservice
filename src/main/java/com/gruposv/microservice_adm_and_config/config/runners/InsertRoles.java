package com.gruposv.microservice_adm_and_config.config.runners;

import com.gruposv.microservice_adm_and_config.modules.system_users.entity.PermissionEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.PermissionRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(4)
public class InsertRoles implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public InsertRoles(PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Buscar permissões
        List<PermissionEntity> permissionsADM = this.permissionRepository.findAll();
        List<PermissionEntity> permissionsCommon = this.permissionRepository.findByPermissionKeyIn(List.of("VIEW_USERS", "VIEW_ROLES", "VIEW_COMPANIES", "VIEW_BRANCHES", "VIEW_PERMISSIONS"));
        if(permissionsADM.isEmpty() || permissionsCommon.isEmpty()){
            new RuntimeException("Não foi possível cadastrar cargos pois não temos permissões cadastradas");
        }

        if(this.roleRepository.findAll().isEmpty()){
            List<RoleEntity> roles = new ArrayList<>();

            roles.add(new RoleEntity(null, "ADM", "Administrador", null, permissionsADM));
            roles.add(new RoleEntity(null, "COMUM", "Usuário comum", null, permissionsCommon));

            this.roleRepository.saveAll(roles);
        }

    }

}
