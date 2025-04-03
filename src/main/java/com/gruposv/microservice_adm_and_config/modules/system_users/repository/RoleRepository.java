package com.gruposv.microservice_adm_and_config.modules.system_users.repository;

import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String roleName);
    List<RoleEntity> findByRoleNameIn(List<String> names);
    boolean existsByRoleName(String roleName);
}
