package com.gruposv.microservice_adm_and_config.modules.system_users.repository;

import com.gruposv.microservice_adm_and_config.modules.system_users.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByPermissionKey(String name);
    List<PermissionEntity> findByPermissionKeyIn(List<String> names);
    boolean existsByPermissionKey(String permissionKey);

}
