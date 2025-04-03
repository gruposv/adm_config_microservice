package com.gruposv.microservice_adm_and_config.modules.system_users.dto;

import com.gruposv.microservice_adm_and_config.modules.system_users.enums.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public class UserDTO {

    private Long id;

    @NotBlank(message = "O nome do usuário é obrigatório.")
    private String username;

    @CPF(message = "O CPF está incorreto.")
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @Email(message = "O E-mail está incorreto.")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    private UserStatus userStatus;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@#$!%^&*.]{6,12}$",
            message = "A senha deve conter de 6 a 12 caracteres, com pelo menos uma letra maiúscula, uma letra minúscula e um número."
    )
    @NotBlank(message = "O Campo \"senha\" é obrigatório.")
    private String password;

    @NotNull(message = "Ao menos um cargo deve ser atribuído ao usuário.")
    @Size(min = 1, message = "O usuário deve ter pelo menos um cargo associado")
    @Valid
    private List<String> roleNames;

    @NotNull(message = "Uma filial deve ser atribuída ao usuário.")
    @Positive(message = "O valor deve ser positivo.")
    private Long branchId;

    @NotBlank(message = "O Campo \"CEP\" é obrigatório.")
    private String postalCode;

    @NotBlank(message = "O Campo \"rua\" é obrigatório.")
    private String street;

    @NotBlank(message = "O Campo \"número\" é obrigatório.")
    private String numberAddress;

    private String complement;

    @NotBlank(message = "O Campo \"bairro\" é obrigatório.")
    private String neighborhood;

    @NotBlank(message = "O Campo \"cidade\" é obrigatório.")
    private String city;

    @NotBlank(message = "O Campo \"Estado\" é obrigatório.")
    private String uf;

    private String ddd;

    private String region;

    private String ibgeCode;

    private String giaCode;

    private String siafiCode;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String cpf, String email, UserStatus userStatus, String password, List<String> roleNames, Long branchId, String postalCode, String street, String numberAddress, String complement, String neighborhood, String city, String uf, String ddd, String region, String ibgeCode, String giaCode, String siafiCode) {
        this.id = id;
        this.username = username;
        this.cpf = cpf;
        this.email = email;
        this.userStatus = userStatus;
        this.password = password;
        this.roleNames = roleNames;
        this.branchId = branchId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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
