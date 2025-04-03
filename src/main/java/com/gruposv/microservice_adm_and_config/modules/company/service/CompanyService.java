package com.gruposv.microservice_adm_and_config.modules.company.service;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.address.repository.AddressRepository;
import com.gruposv.microservice_adm_and_config.modules.company.dto.CompanyDTO;
import com.gruposv.microservice_adm_and_config.modules.company.exception.CompanyNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.company.dto.ListCompaniesDTO;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;
import com.gruposv.microservice_adm_and_config.modules.company.mapper.CompanyDTOMapper;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UniqueDataException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    public CompanyDTO create(CompanyDTO companyDTO){
        // Validações
        if(companyDTO.getTradeName() != null && this.companyRepository.existsByTradeName(companyDTO.getTradeName())) throw new UniqueDataException("O nome fantasia já existe, portanto não é possível realizar o cadastro");
        if(companyDTO.getLegalName() != null && this.companyRepository.existsByLegalName(companyDTO.getLegalName())) throw new UniqueDataException("Essa Razão Social já existe, portanto não é possível realizar o cadastro");
        if(companyDTO.getCnpj() != null && this.companyRepository.existsByCnpj(companyDTO.getCnpj())) throw new UniqueDataException("O CNPJ informado já existe, portanto não é possível realizar o cadastro");
        if(companyDTO.getEmail() != null && this.companyRepository.existsByEmail(companyDTO.getEmail())) throw new UniqueDataException("O E-mail informado já existe, portanto não é possível realizar o cadastro");

        CompanyEntity company = CompanyDTOMapper.toEntity(companyDTO);
        AddressEntity address = this.addressRepository.save(company.getAddress());
        company.setAddress(address);
        return CompanyDTOMapper.toDTO(this.companyRepository.save(company));
    }

    public Page<CompanyDTO> findAll(ListCompaniesDTO companiesDTO){
        Pageable pageable = PageRequest.of(companiesDTO.page(), companiesDTO.size());
        return this.companyRepository.findAll(pageable).map(companyEntity -> CompanyDTOMapper.toDTO(companyEntity));
    }

    public CompanyDTO findById(Long id){
        CompanyEntity company = this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("A empresa com o ID " + id + " não foi encontrado."));
        return CompanyDTOMapper.toDTO(company);
    }

    @Transactional
    public CompanyDTO update(Long id, CompanyDTO companyDTO){
        // Validações
        if(companyDTO.getTradeName() != null && this.companyRepository.existsByTradeName(companyDTO.getTradeName())) throw new UniqueDataException("O nome fantasia já existe, portanto não é possível realizar o cadastro");
        if(companyDTO.getLegalName() != null && this.companyRepository.existsByLegalName(companyDTO.getLegalName())) throw new UniqueDataException("Essa Razão Social já existe, portanto não é possível realizar o cadastro");
        if(companyDTO.getCnpj() != null && this.companyRepository.existsByCnpj(companyDTO.getCnpj())) throw new UniqueDataException("O CNPJ informado já existe, portanto não é possível realizar o cadastro");
        if(companyDTO.getEmail() != null && this.companyRepository.existsByEmail(companyDTO.getEmail())) throw new UniqueDataException("O E-mail informado já existe, portanto não é possível realizar o cadastro");

        CompanyEntity company = this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("A empresa com o ID " + id + " não foi encontrado."));
        CompanyEntity companyUpdated = CompanyDTOMapper.toUpdateEntity(companyDTO, company);
        companyUpdated.setLastUpdateDate(LocalDateTime.now());
        return CompanyDTOMapper.toDTO(this.companyRepository.save(companyUpdated));
    }

    public void delete(long id){
        CompanyEntity company = this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("A empresa com o ID " + id + " não foi encontrado."));
        this.companyRepository.delete(company);
    }
}
