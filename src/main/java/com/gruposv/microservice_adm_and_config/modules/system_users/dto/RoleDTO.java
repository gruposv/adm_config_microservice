package com.gruposv.microservice_adm_and_config.modules.system_users.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RoleDTO(
        Long id,
        @NotEmpty(message = "O nome do crago é obrigatório")
        String roleName,
        String description,
        @NotEmpty(message = "A lista de permissões não pode estar vazia")
        @Size(min = 1, message = "O cargo deve ter pelo menos uma permissão")
        @Valid
        List<String> permissionsName
) {
}
