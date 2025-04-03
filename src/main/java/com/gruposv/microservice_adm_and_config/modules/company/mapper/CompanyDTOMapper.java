package com.gruposv.microservice_adm_and_config.modules.company.mapper;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.company.dto.CompanyDTO;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;

public class CompanyDTOMapper {

    public static CompanyEntity toEntity(CompanyDTO dto) {
        if (dto == null) {
            return null;
        }

        CompanyEntity entity = new CompanyEntity();
        entity.setId(dto.getId());
        entity.setTradeName(dto.getTradeName());
        entity.setLegalName(dto.getLegalName());
        entity.setCnpj(dto.getCnpj());
        entity.setStateRegistration(dto.getStateRegistration());
        entity.setMunicipalRegistration(dto.getMunicipalRegistration());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setWebsite(dto.getWebsite());

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

    public static CompanyDTO toDTO(CompanyEntity company) {
        if (company == null) {
            return null;
        }

        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setTradeName(company.getTradeName());
        dto.setLegalName(company.getLegalName());
        dto.setCnpj(company.getCnpj());
        dto.setStateRegistration(company.getStateRegistration());
        dto.setMunicipalRegistration(company.getMunicipalRegistration());
        dto.setPhone(company.getPhone());
        dto.setEmail(company.getEmail());
        dto.setWebsite(company.getWebsite());

        if (company.getAddress() != null) {
            dto.setPostalCode(company.getAddress().getPostalCode());
            dto.setStreet(company.getAddress().getStreet());
            dto.setNumberAddress(company.getAddress().getNumberAddress());
            dto.setComplement(company.getAddress().getComplement());
            dto.setNeighborhood(company.getAddress().getNeighborhood());
            dto.setCity(company.getAddress().getCity());
            dto.setUf(company.getAddress().getUf());
            dto.setDdd(company.getAddress().getDdd());
            dto.setRegion(company.getAddress().getRegion());
            dto.setIbgeCode(company.getAddress().getIbgeCode());
            dto.setGiaCode(company.getAddress().getGiaCode());
            dto.setSiafiCode(company.getAddress().getSiafiCode());
        }

        return dto;
    }

    public static CompanyEntity toUpdateEntity(CompanyDTO dto, CompanyEntity entity) {
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

        return entity;
    }

    private static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }
}

