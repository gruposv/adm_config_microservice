package com.gruposv.microservice_adm_and_config.modules.system_users.mapper;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.UserDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTOMapper {

    public static UserDTO toDTO(UserEntity user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setUserStatus(user.getUserStatus());
        dto.setRoleNames(user.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toList()));
        dto.setBranchId(user.getBranch() != null ? user.getBranch().getId() : null);

        if (user.getAddress() != null) {
            dto.setPostalCode(user.getAddress().getPostalCode());
            dto.setStreet(user.getAddress().getStreet());
            dto.setNumberAddress(user.getAddress().getNumberAddress());
            dto.setComplement(user.getAddress().getComplement());
            dto.setNeighborhood(user.getAddress().getNeighborhood());
            dto.setCity(user.getAddress().getCity());
            dto.setUf(user.getAddress().getUf());
        }

        return dto;
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        user.setUserStatus(userDTO.getUserStatus());
        user.setPasswordHash(userDTO.getPassword());

        List<RoleEntity> roles = userDTO.getRoleNames().stream()
                .map(roleName -> {
                    RoleEntity role = new RoleEntity();
                    role.setRoleName(roleName);
                    return role;
                }).collect(Collectors.toList());
        user.setRoles(roles);

        if (userDTO.getBranchId() != null) {
            CompanyBranchEntity branch = new CompanyBranchEntity();
            branch.setId(userDTO.getBranchId());
            user.setBranch(branch);
        }

        AddressEntity address = new AddressEntity();
        address.setPostalCode(userDTO.getPostalCode());
        address.setStreet(userDTO.getStreet());
        address.setNumberAddress(userDTO.getNumberAddress());
        address.setComplement(userDTO.getComplement());
        address.setNeighborhood(userDTO.getNeighborhood());
        address.setCity(userDTO.getCity());
        address.setUf(userDTO.getUf());
        user.setAddress(address);

        return user;
    }

    public static UserEntity toUpdateEntity(UserDTO userDTO, UserEntity existingUser) {
        if (userDTO == null || existingUser == null) {
            return null;
        }

        if (isValid(userDTO.getUsername())) {
            existingUser.setUsername(userDTO.getUsername());
        }

        if (isValid(userDTO.getEmail())) {
            existingUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getUserStatus() != null) {
            existingUser.setUserStatus(userDTO.getUserStatus());
        }

        if (isValid(userDTO.getPassword())) {
            existingUser.setPasswordHash(userDTO.getPassword());
        }

        if (userDTO.getBranchId() != null) {
            CompanyBranchEntity branch = new CompanyBranchEntity();
            branch.setId(userDTO.getBranchId());
            existingUser.setBranch(branch);
        }

        if (existingUser.getAddress() == null) {
            existingUser.setAddress(new AddressEntity());
        }

        AddressEntity address = existingUser.getAddress();

        if (isValid(userDTO.getPostalCode())) {
            address.setPostalCode(userDTO.getPostalCode());
        }
        if (isValid(userDTO.getStreet())) {
            address.setStreet(userDTO.getStreet());
        }
        if (isValid(userDTO.getNumberAddress())) {
            address.setNumberAddress(userDTO.getNumberAddress());
        }
        if (isValid(userDTO.getComplement())) {
            address.setComplement(userDTO.getComplement());
        }
        if (isValid(userDTO.getNeighborhood())) {
            address.setNeighborhood(userDTO.getNeighborhood());
        }
        if (isValid(userDTO.getCity())) {
            address.setCity(userDTO.getCity());
        }
        if (isValid(userDTO.getUf())) {
            address.setUf(userDTO.getUf());
        }

        existingUser.setAddress(address);
        existingUser.setUpdatedAt(LocalDateTime.now());

        return existingUser;
    }

    private static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
