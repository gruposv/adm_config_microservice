package com.gruposv.microservice_adm_and_config.modules.address.entity;

import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "postal_code", length = 10)
    private String postalCode; // CEP

    @Column(name = "street")
    private String street; // Rua

    @Column(name = "numberAddress")
    private String numberAddress;

    @Column(name = "complement")
    private String complement; // Complemento

    @Column(name = "neighborhood")
    private String neighborhood; // Bairro

    @Column(name = "city")
    private String city; // Cidade

    @Column(name = "uf", length = 2)
    private String uf; // UF - Estado

    @Column(name = "ddd_number", length = 3)
    private String ddd; // DDD

    @Column(name = "region")
    private String region; // Região

    @Column(name = "ibge_code")
    private String ibgeCode; // IBGE

    @Column(name = "gia_code")
    private String giaCode; // GIA

    @Column(name = "siafi_code")
    private String siafiCode; // SIAFI

    @OneToOne(mappedBy = "address")
    private UserEntity user;

    @OneToOne(mappedBy = "address")
    private CompanyEntity company;

    @OneToOne(mappedBy = "address")
    private CompanyBranchEntity branch;

    public AddressEntity() {
    }

    public AddressEntity(Long id, String postalCode, String street, String numberAddress, String complement, String neighborhood, String city, String uf, String ddd, String region, String ibgeCode, String giaCode, String siafiCode) {
        this.id = id;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public CompanyBranchEntity getBranch() {
        return branch;
    }

    public void setBranch(CompanyBranchEntity branch) {
        this.branch = branch;
    }
}
