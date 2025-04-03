package com.gruposv.microservice_adm_and_config.modules.system_users.controller;

import com.gruposv.microservice_adm_and_config.modules.system_users.entity.PermissionEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.service.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_PERMISSIONS')")
    public List<PermissionEntity> findAllPermissions(){
        return this.permissionService.findAll();
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('VIEW_PERMISSIONS')")
    public PermissionEntity findPermissionById(@PathVariable Long id){
        return this.permissionService.findById(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('VIEW_PERMISSIONS')")
    public PermissionEntity findPermissionByName(@PathVariable("name") String name){
        return this.permissionService.findByPermissionKey(name);
    }

}
