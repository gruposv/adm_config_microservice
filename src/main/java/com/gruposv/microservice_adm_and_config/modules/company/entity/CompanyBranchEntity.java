package com.gruposv.microservice_adm_and_config.modules.company.entity;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_company_branches")
public class CompanyBranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;

    @Column(name = "trade_name", unique = true, nullable = false)
    private String tradeName; // Nome Fantásia da empresa

    @Column(name = "legal_name", unique = true, nullable = false)
    private String legalName; // Razão social

    @Column(name = "cnpj", unique = true, nullable = false, length = 14)
    private String cnpj;

    @Column(name = "state_registration", length = 20)
    private String stateRegistration; // Inscrição estadual da empresa

    @Column(name = "municipal_registration", length = 20)
    private String municipalRegistration; // Inscrição municipal da empresa

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate; // Data de registro da empresa no sistema

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @Column(name = "responsible")
    private String responsible;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<UserEntity> users;

    public CompanyBranchEntity() {
    }

    public CompanyBranchEntity(Long id, String tradeName, String legalName, String cnpj, String stateRegistration, String municipalRegistration, String phone, String email, String website, LocalDateTime registrationDate, LocalDateTime lastUpdateDate, String responsible, AddressEntity address, CompanyEntity company, List<UserEntity> users) {
        this.id = id;
        this.tradeName = tradeName;
        this.legalName = legalName;
        this.cnpj = cnpj;
        this.stateRegistration = stateRegistration;
        this.municipalRegistration = municipalRegistration;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.registrationDate = registrationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.responsible = responsible;
        this.address = address;
        this.company = company;
        this.users = users;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
