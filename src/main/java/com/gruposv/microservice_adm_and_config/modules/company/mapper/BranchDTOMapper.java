package com.gruposv.microservice_adm_and_config.modules.company.mapper;


import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.company.dto.BranchDTO;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;

import java.time.LocalDateTime;

public class BranchDTOMapper {

    public static CompanyBranchEntity toEntity(BranchDTO dto, CompanyEntity company) {
        if (dto == null || company == null) {
            return null;
        }

        CompanyBranchEntity entity = new CompanyBranchEntity();
        entity.setId(dto.getId());
        entity.setTradeName(dto.getTradeName());
        entity.setLegalName(dto.getLegalName());
        entity.setCnpj(dto.getCnpj());
        entity.setStateRegistration(dto.getStateRegistration());
        entity.setMunicipalRegistration(dto.getMunicipalRegistration());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setWebsite(dto.getWebsite());
        entity.setResponsible(dto.getResponsible());
        entity.setCompany(company);

        AddressEntity address = new AddressEntity();
        address.setPostalCode(dto.getPostalCode());
        address.setStreet(dto.getStreet());
        address.setNumberAddress(dto.getNumberAddress());
        address.setComplement(dto.getComplement());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setUf(dto.getUf());
        address.setDdd(dto.getDdd());
        address.setRegion(dto.getRegion());
        address.setIbgeCode(dto.getIbgeCode());
        address.setGiaCode(dto.getGiaCode());
        address.setSiafiCode(dto.getSiafiCode());

        entity.setAddress(address);

        return entity;
    }

    public static BranchDTO toDTO(CompanyBranchEntity entity) {
        if (entity == null) {
            return null;
        }

        BranchDTO dto = new BranchDTO();
        dto.setId(entity.getId());
        dto.setTradeName(entity.getTradeName());
        dto.setLegalName(entity.getLegalName());
        dto.setCnpj(entity.getCnpj());
        dto.setStateRegistration(entity.getStateRegistration());
        dto.setMunicipalRegistration(entity.getMunicipalRegistration());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setWebsite(entity.getWebsite());
        dto.setResponsible(entity.getResponsible());
        dto.setRegistrationDate(entity.getRegistrationDate());
        dto.setLastUpdateDate(entity.getLastUpdateDate());

        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
        }

        if (entity.getAddress() != null) {
            dto.setPostalCode(entity.getAddress().getPostalCode());
            dto.setStreet(entity.getAddress().getStreet());
            dto.setNumberAddress(entity.getAddress().getNumberAddress());
            dto.setComplement(entity.getAddress().getComplement());
            dto.setNeighborhood(entity.getAddress().getNeighborhood());
            dto.setCity(entity.getAddress().getCity());
            dto.setUf(entity.getAddress().getUf());
            dto.setDdd(entity.getAddress().getDdd());
            dto.setRegion(entity.getAddress().getRegion());
            dto.setIbgeCode(entity.getAddress().getIbgeCode());
            dto.setGiaCode(entity.getAddress().getGiaCode());
            dto.setSiafiCode(entity.getAddress().getSiafiCode());
        }

        return dto;
    }

    public static CompanyBranchEntity toUpdateEntity(CompanyBranchEntity entity, BranchDTO dto) {
        if (entity == null || dto == null) {
            return null;
        }

        if (isValid(dto.getTradeName())) entity.setTradeName(dto.getTradeName());
        if (isValid(dto.getLegalName())) entity.setLegalName(dto.getLegalName());
        if (isValid(dto.getCnpj())) entity.setCnpj(dto.getCnpj());
        if (isValid(dto.getStateRegistration())) entity.setStateRegistration(dto.getStateRegistration());
        if (isValid(dto.getMunicipalRegistration())) entity.setMunicipalRegistration(dto.getMunicipalRegistration());
        if (isValid(dto.getPhone())) entity.setPhone(dto.getPhone());
        if (isValid(dto.getEmail())) entity.setEmail(dto.getEmail());
        if (isValid(dto.getWebsite())) entity.setWebsite(dto.getWebsite());
        if (isValid(dto.getResponsible())) entity.setResponsible(dto.getResponsible());

        if (entity.getAddress() == null) {
            entity.setAddress(new AddressEntity());
        }

        AddressEntity address = entity.getAddress();

        if (isValid(dto.getPostalCode())) address.setPostalCode(dto.getPostalCode());
        if (isValid(dto.getStreet())) address.setStreet(dto.getStreet());
        if (isValid(dto.getNumberAddress())) address.setNumberAddress(dto.getNumberAddress());
        if (isValid(dto.getComplement())) address.setComplement(dto.getComplement());
        if (isValid(dto.getNeighborhood())) address.setNeighborhood(dto.getNeighborhood());
        if (isValid(dto.getCity())) address.setCity(dto.getCity());
        if (isValid(dto.getUf())) address.setUf(dto.getUf());
        if (isValid(dto.getDdd())) address.setDdd(dto.getDdd());
        if (isValid(dto.getRegion())) address.setRegion(dto.getRegion());
        if (isValid(dto.getIbgeCode())) address.setIbgeCode(dto.getIbgeCode());
        if (isValid(dto.getGiaCode())) address.setGiaCode(dto.getGiaCode());
        if (isValid(dto.getSiafiCode())) address.setSiafiCode(dto.getSiafiCode());

        entity.setLastUpdateDate(LocalDateTime.now());

        return entity;
    }

    private static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }
}

