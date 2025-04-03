package com.gruposv.microservice_adm_and_config.modules.address.repository;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
