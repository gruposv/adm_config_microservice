package com.gruposv.microservice_adm_and_config.modules.system_users.service;

import com.gruposv.microservice_adm_and_config.modules.system_users.entity.PermissionEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.PermissionNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UniqueDataException;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public PermissionEntity create(PermissionEntity permission){
        if(permission.getPermissionKey() != null && this.permissionRepository.existsByPermissionKey(permission.getPermissionKey())) throw new UniqueDataException("Essa permissão já foi cadastrada.");
        return this.permissionRepository.save(permission);
    }

    public List<PermissionEntity> findAll(){
        return this.permissionRepository.findAll();
    }

    public PermissionEntity findById(Long id){
        return this.permissionRepository.findById(id).orElseThrow(() -> new PermissionNotFoundException("Permissão com o ID: " + id + " não existe."));
    }

    public PermissionEntity findByPermissionKey(String name){
        return this.permissionRepository.findByPermissionKey(name)
                .orElseThrow(() -> new PermissionNotFoundException("A permissão " + name + " não foi encontrada"));
    }

}
