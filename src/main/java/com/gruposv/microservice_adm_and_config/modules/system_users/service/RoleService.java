package com.gruposv.microservice_adm_and_config.modules.system_users.service;

import com.gruposv.microservice_adm_and_config.modules.system_users.dto.ListRolesDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.RoleDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.PermissionEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.PermissionNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.RoleNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UniqueDataException;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.PermissionRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public RoleEntity create(RoleEntity role){
        return this.roleRepository.save(role);
    }

    @Transactional
    public RoleEntity create(RoleDTO roleDTO){

        if(roleDTO.roleName() != null && this.roleRepository.existsByRoleName(roleDTO.roleName())) throw new UniqueDataException("O nome do cargo informado já existe, portanto não pode ser cadastrado.");

        RoleEntity role = new RoleEntity();
        role.setRoleName(roleDTO.roleName());
        role.setDescription(roleDTO.description());

        // Buscar permissões para inserir na Role baseadas no DTO
        List<PermissionEntity> permissions = this.permissionRepository.findByPermissionKeyIn(roleDTO.permissionsName());
        if(permissions.isEmpty()){
            new PermissionNotFoundException("Nenhuma permissão foi encontrada, portanto não é possível cadastrar o cargo.");
        }
        role.setPermissions(permissions);
        return this.roleRepository.save(role);
    }

    public Page<RoleEntity> findAll(ListRolesDTO dto){
        Pageable pageable = PageRequest.of(dto.page(), dto.size());
        return this.roleRepository.findAll(pageable);
    }

    public RoleEntity findById(Long id){
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("O cargo não foi encontrado."));
    }

    public RoleEntity findByRoleName(String roleName){
        return this.roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("O cargo não foi encontrado."));
    }

    @Transactional
    public RoleEntity update(Long id, RoleDTO roleDTO){
        if(roleDTO.roleName() != null && this.roleRepository.existsByRoleName(roleDTO.roleName())) throw new UniqueDataException("O nome do cargo informado já existe, portanto não pode ser editado.");
        RoleEntity roleEntity = this.findById(id);
        roleEntity.setRoleName(roleDTO.roleName());
        roleEntity.setDescription(roleDTO.description());
        List<PermissionEntity> permissions = this.permissionRepository.findByPermissionKeyIn(roleDTO.permissionsName());
        if(permissions.isEmpty()){
            new PermissionNotFoundException("Nenhuma permissão foi encontrada, portanto não é possível editar o cargo.");
        }
        roleEntity.setPermissions(permissions);
        return this.roleRepository.save(roleEntity);
    }

    public void delete(Long id){
        RoleEntity role = this.findById(id);
        this.roleRepository.delete(role);
    }

}
