package com.gruposv.microservice_adm_and_config.modules.system_users.controller;

import com.gruposv.microservice_adm_and_config.dto.ApiResponseDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.ListRolesDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.RoleDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.mapper.RoleDTOMapper;
import com.gruposv.microservice_adm_and_config.modules.system_users.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> create(@RequestBody @Valid RoleDTO roleDTO){
        RoleDTO dto = RoleDTOMapper.toDTO(this.roleService.create(roleDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), dto, "Cargo criado com sucesso!", List.of()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ROLES')")
    public ResponseEntity<ApiResponseDTO<Page<RoleDTO>>> listRoles(@ModelAttribute @Valid ListRolesDTO listRolesDTO){
        Page<RoleDTO> roleDTOPage = this.roleService.findAll(listRolesDTO).map(roleEntity -> RoleDTOMapper.toDTO(roleEntity));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), roleDTOPage, "Sucesso!", List.of()));
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('VIEW_ROLES')")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> findById(@PathVariable Long id){
        RoleDTO roleDTO = RoleDTOMapper.toDTO(this.roleService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), roleDTO, "Sucesso!", List.of()));
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('VIEW_ROLES')")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> findByRoleName(@PathVariable("name") String roleName){
        RoleDTO roleDTO = RoleDTOMapper.toDTO(this.roleService.findByRoleName(roleName));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), roleDTO, "Sucesso!", List.of()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> update(@PathVariable Long id, @RequestBody @Valid RoleDTO roleDTO){
        RoleDTO dto = RoleDTOMapper.toDTO(this.roleService.update(id, roleDTO));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), dto, "Sucesso!", List.of()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    public ResponseEntity<ApiResponseDTO<Void>> delete(@PathVariable Long id){
        this.roleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), null, "Cargo deletado com sucesso!", List.of()));
    }
}
