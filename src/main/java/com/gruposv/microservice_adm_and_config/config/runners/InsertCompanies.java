package com.gruposv.microservice_adm_and_config.config.runners;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.address.repository.AddressRepository;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
public class InsertCompanies implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    public InsertCompanies(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if(this.companyRepository.findAll().isEmpty()){
            List<CompanyEntity> companies = new ArrayList<>();
            companies.add(new CompanyEntity(null, "ORIGINAL PORTAS", "ORIGINAL PORTAS", "35082391000115", null, null, "11111111", "originalportas@email.com", "https://originalportas.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null));
            companies.add(new CompanyEntity(null, "MEGATRON", "MEGATRON", "39982926000165", null, null, "22222222", "megatron@email.com", "https://megatron.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null));
            companies.add(new CompanyEntity(null, "SVCONNECT", "SVCONNECT", "24342578000194", null, null, "33333333", "svconnect@email.com", "https://svconnect.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null));

            this.companyRepository.saveAll(companies);
        }

    }

}
