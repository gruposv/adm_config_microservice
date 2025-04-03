package com.gruposv.microservice_adm_and_config.modules.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public class CompanyDTO {

    private Long id;

    @NotBlank(message = "A Empresa precisa ter um nome fantasia.")
    private String tradeName; // Nome Fantásia da empresa

    @NotBlank(message = "A Empresa precisa ter uma razão social.")
    private String legalName; // Razão social

    @NotBlank(message = "A Empresa precisa ter um CNPJ.")
    @CNPJ
    private String cnpj;

    private String stateRegistration; // Inscrição estadual da empresa

    private String municipalRegistration; // Inscrição municipal da empresa

    @NotBlank(message = "A Empresa precisa ter um telefone.")
    private String phone;

    @NotBlank(message = "A Empresa precisa ter um e-mail.")
    @Email(message = "O e-mail foi digitado incorretamente.")
    private String email;

    private String website;

    @NotBlank(message = "A Empresa precisa ter CEP.")
    private String postalCode; // CEP

    @NotBlank(message = "O Campo \"Rua\" é obrigatório.")
    private String street; // Rua

    @NotBlank(message = "O Campo \"Número de endereço\" é obrigatório.")
    private String numberAddress;

    private String complement; // Complemento

    @NotBlank(message = "O Campo \"Bairro\" é obrigatório.")
    private String neighborhood; // Bairro

    @NotBlank(message = "O Campo \"Cidade\" é obrigatório.")
    private String city; // Cidade

    @NotBlank(message = "O Campo \"UF\" é obrigatório.")
    private String uf; // UF - Estado

    private String ddd; // DDD

    private String region; // Região

    private String ibgeCode; // IBGE

    private String giaCode; // GIA

    private String siafiCode; // SIAFI

    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String tradeName, String legalName, String cnpj, String stateRegistration, String municipalRegistration, String phone, String email, String website, String postalCode, String street, String numberAddress, String complement, String neighborhood, String city, String uf, String ddd, String region, String ibgeCode, String giaCode, String siafiCode) {
        this.id = id;
        this.tradeName = tradeName;
        this.legalName = legalName;
        this.cnpj = cnpj;
        this.stateRegistration = stateRegistration;
        this.municipalRegistration = municipalRegistration;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.postalCode = postalCode;
        this.street = street;
        this.numberAddress = numberAddress;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
        this.ddd = ddd;
        this.region = region;
        this.ibgeCode = ibgeCode;
        this.giaCode = giaCode;
        this.siafiCode = siafiCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getStateRegistration() {
        return stateRegistration;
    }

    public void setStateRegistration(String stateRegistration) {
        this.stateRegistration = stateRegistration;
    }

    public String getMunicipalRegistration() {
        return municipalRegistration;
    }

    public void setMunicipalRegistration(String municipalRegistration) {
        this.municipalRegistration = municipalRegistration;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberAddress() {
        return numberAddress;
    }

    public void setNumberAddress(String numberAddress) {
        this.numberAddress = numberAddress;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public String getGiaCode() {
        return giaCode;
    }

    public void setGiaCode(String giaCode) {
        this.giaCode = giaCode;
    }

    public String getSiafiCode() {
        return siafiCode;
    }

    public void setSiafiCode(String siafiCode) {
        this.siafiCode = siafiCode;
    }
}
