package com.gruposv.microservice_adm_and_config.modules.system_users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_permissions")
public class PermissionEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "permission_key", nullable = false, length = 100, unique = true)
    private String permissionKey;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
    private List<RoleEntity> roles = new ArrayList<>();

    public PermissionEntity() {
    }

    public PermissionEntity(Long id, String permissionKey, String description, List<RoleEntity> roles) {
        this.id = id;
        this.permissionKey = permissionKey;
        this.description = description;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
