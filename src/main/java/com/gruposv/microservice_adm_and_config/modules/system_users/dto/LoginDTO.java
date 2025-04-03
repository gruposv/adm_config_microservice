package com.gruposv.microservice_adm_and_config.modules.system_users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginDTO(

        @NotBlank(message = "O e-mail é obrigatório para realizar o login.")
        @Email(message = "O E-mail está informado incorretamente.")
        String email,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@#$!%^&*.]{6,12}$",
                message = "A senha deve conter de 6 a 12 caracteres, com pelo menos uma letra maiúscula, uma letra minúscula e um número."
        )
        @NotBlank(message = "O Campo \"senha\" é obrigatório.")
        String password
) {
}
