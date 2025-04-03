package com.gruposv.microservice_adm_and_config.config.runners;

import com.gruposv.microservice_adm_and_config.modules.system_users.entity.PermissionEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.PermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
public class InsertPermissions implements CommandLineRunner {

    private final PermissionRepository permissionRepository;

    public InsertPermissions(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if(this.permissionRepository.findAll().isEmpty()){

            // Por enquanto somente permissões de CRUD de usuário
            List<PermissionEntity> permisions = new ArrayList<>();
            permisions.add(new PermissionEntity(null, "CREATE_USER", "Permissão para criar usuários", null));
            permisions.add(new PermissionEntity(null, "VIEW_USERS", "Permissão para consultar dados de usuários", null));
            permisions.add(new PermissionEntity(null, "UPDATE_USER", "Permissão para editar usuários", null));
            permisions.add(new PermissionEntity(null, "DELETE_USER", "Permissão de deletar usuários", null));
            permisions.add(new PermissionEntity(null, "CREATE_ROLE", "Permissão de criar novos cargos", null));
            permisions.add(new PermissionEntity(null, "VIEW_ROLES", "Permissão de visualizar os cargos", null));
            permisions.add(new PermissionEntity(null, "UPDATE_ROLE", "Permissão de editar cargos", null));
            permisions.add(new PermissionEntity(null, "DELETE_ROLE", "Permissão de excluir cargos", null));
            permisions.add(new PermissionEntity(null, "VIEW_PERMISSIONS", "Permissão de visualizar permissões", null));
            permisions.add(new PermissionEntity(null, "VIEW_COMPANIES", "Permissão de visualizar empresas", null));
            permisions.add(new PermissionEntity(null, "CREATE_COMPANIES", "Permissão de criar novas empresas", null));
            permisions.add(new PermissionEntity(null, "UPDATE_COMPANIES", "Permissão de edtar informações das empresas cadastradas", null));
            permisions.add(new PermissionEntity(null, "DELETE_COMPANIES", "Permissão de deletar empresas do sistema", null));
            permisions.add(new PermissionEntity(null, "VIEW_BRANCHES", "Permissão de visualizar filiais", null));
            permisions.add(new PermissionEntity(null, "CREATE_BRANCHES", "Permissão de criar novas filiais de uma empresa.", null));
            permisions.add(new PermissionEntity(null, "UPDATE_BRANCHES", "Permissão de editar informações de filiais.", null));
            permisions.add(new PermissionEntity(null, "DELETE_BRANCHES", "Permissão de visualizar permissões", null));

            this.permissionRepository.saveAll(permisions);

        }

    }

}
