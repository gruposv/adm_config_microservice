package com.gruposv.microservice_adm_and_config.modules.company.repository;

import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByTradeName(String tradeName);
    boolean existsByLegalName(String LegalName);
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
}
