package com.gruposv.microservice_adm_and_config.config.runners;

import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyBranchRepository;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class InsertBranches implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CompanyBranchRepository branchRepository;

    public InsertBranches(CompanyRepository companyRepository, CompanyBranchRepository branchRepository) {
        this.companyRepository = companyRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Buscar todas as empresas
        List<CompanyEntity> companies = companyRepository.findAll();
        if(companies.isEmpty()){
            new RuntimeException("Não é possivel cadastrar as filiais pois não tem nenhuma empresa cadastrada");
        }

        if(this.branchRepository.findAll().isEmpty()){
            List<CompanyBranchEntity> braches = new ArrayList<>();

            braches.add(new CompanyBranchEntity(null, "Filial 1 da Empresa 1", "Filial 1 da Empresa 1", "52579503000167", null, null, "11111111", "filial1.empresa1@email.com", "https://filial1.empresa1.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(0), null));
            braches.add(new CompanyBranchEntity(null, "Filial 2 da Empresa 1", "Filial 2 da Empresa 1", "69458441000138", null, null, "22222222", "filial2.empresa1@email.com", "https://filial2.empresa1.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(0), null));
            braches.add(new CompanyBranchEntity(null, "Filial 3 da Empresa 1", "Filial 3 da Empresa 1", "72645143000161", null, null, "33333333", "filial3.empresa1@email.com", "https://filial3.empresa1.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(0), null));
            braches.add(new CompanyBranchEntity(null, "Filial 4 da Empresa 1", "Filial 4 da Empresa 1", "61247163000168", null, null, "44444444", "filial4.empresa1@email.com", "https://filial4.empresa1.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(0), null));
            braches.add(new CompanyBranchEntity(null, "Filial 1 da Empresa 2", "Filial 1 da Empresa 2", "00700535000175", null, null, "55555555", "filial1.empresa2@email.com", "https://filial1.empresa2.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(1), null));
            braches.add(new CompanyBranchEntity(null, "Filial 1 da Empresa 3", "Filial 1 da Empresa 3", "93643263000108", null, null, "66666666", "filial1.empresa3@email.com", "https://filial1.empresa3.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(2), null));
            braches.add(new CompanyBranchEntity(null, "Filial 2 da Empresa 3", "Filial 2 da Empresa 3", "44062304000159", null, null, "77777777", "filial2.empresa3@email.com", "https://filial2.empresa3.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(2), null));
            braches.add(new CompanyBranchEntity(null, "Filial 3 da Empresa 3", "Filial 3 da Empresa 3", "22829362000122", null, null, "88888888", "filial3.empresa3@email.com", "https://filial3.empresa3.com.br", LocalDateTime.now(), LocalDateTime.now(), null, null, companies.get(2), null));

            this.branchRepository.saveAll(braches);
        }


    }

}
