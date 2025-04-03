package com.gruposv.microservice_adm_and_config.modules.company.service;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.address.mapper.CreateAddressMapper;
import com.gruposv.microservice_adm_and_config.modules.address.repository.AddressRepository;
import com.gruposv.microservice_adm_and_config.modules.company.dto.BranchDTO;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;
import com.gruposv.microservice_adm_and_config.modules.company.exception.BranchNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.company.exception.CompanyNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.company.mapper.BranchDTOMapper;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyBranchRepository;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UniqueDataException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyBranchService {

    private final CompanyBranchRepository companyBranchRepository;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    public CompanyBranchService(CompanyBranchRepository companyBranchRepository, CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyBranchRepository = companyBranchRepository;
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public BranchDTO create(BranchDTO branchDTO){

        // Validações
        if(branchDTO.getTradeName() != null && this.companyRepository.existsByTradeName(branchDTO.getTradeName())) throw new UniqueDataException("O nome fantasia já existe, portanto não é possível realizar o cadastro");
        if(branchDTO.getLegalName() != null && this.companyRepository.existsByLegalName(branchDTO.getLegalName())) throw new UniqueDataException("Essa Razão Social já existe, portanto não é possível realizar o cadastro");
        if(branchDTO.getCnpj() != null && this.companyRepository.existsByCnpj(branchDTO.getCnpj())) throw new UniqueDataException("O CNPJ informado já existe, portanto não é possível realizar o cadastro");
        if(branchDTO.getEmail() != null && this.companyRepository.existsByEmail(branchDTO.getEmail())) throw new UniqueDataException("O E-mail informado já existe, portanto não é possível realizar o cadastro");

        CompanyEntity company = this.companyRepository.findById(branchDTO.getCompanyId()).orElseThrow(() -> new CompanyNotFoundException("A empresa com o ID " + branchDTO.getCompanyId() + " Não existe."));
        CompanyBranchEntity branch = BranchDTOMapper.toEntity(branchDTO, company);
        AddressEntity addressCreated = this.addressRepository.save(branch.getAddress());
        branch.setAddress(addressCreated);
        return BranchDTOMapper.toDTO(this.companyBranchRepository.save(branch));
    }

    public List<BranchDTO> findAll(){
        return this.companyBranchRepository.findAll().stream().map(branch -> BranchDTOMapper.toDTO(branch)).toList();
    }

    public BranchDTO findById(Long id){
        return BranchDTOMapper.toDTO(this.companyBranchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException("A filial com o ID " + id + " não foi econtrada.")));
    }

    @Transactional
    public BranchDTO update(Long id, BranchDTO branchDTO){

        // Validações
        if(branchDTO.getTradeName() != null && this.companyRepository.existsByTradeName(branchDTO.getTradeName())) throw new UniqueDataException("O nome fantasia já existe, portanto não é possível realizar o cadastro");
        if(branchDTO.getLegalName() != null && this.companyRepository.existsByLegalName(branchDTO.getLegalName())) throw new UniqueDataException("Essa Razão Social já existe, portanto não é possível realizar o cadastro");
        if(branchDTO.getCnpj() != null && this.companyRepository.existsByCnpj(branchDTO.getCnpj())) throw new UniqueDataException("O CNPJ informado já existe, portanto não é possível realizar o cadastro");
        if(branchDTO.getEmail() != null && this.companyRepository.existsByEmail(branchDTO.getEmail())) throw new UniqueDataException("O E-mail informado já existe, portanto não é possível realizar o cadastro");

        CompanyBranchEntity companyBranchEntity = this.companyBranchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException("A filial com o ID " + id + " não foi econtrada."));
        CompanyBranchEntity branch = BranchDTOMapper.toUpdateEntity(companyBranchEntity, branchDTO);
        branch.setAddress(branch.getAddress());
        return BranchDTOMapper.toDTO(this.companyBranchRepository.save(branch));
    }

    public void delete(Long id){
        CompanyBranchEntity branch = this.companyBranchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException("A filial com o ID " + id + " não foi econtrada."));
        this.companyBranchRepository.delete(branch);
    }
}
