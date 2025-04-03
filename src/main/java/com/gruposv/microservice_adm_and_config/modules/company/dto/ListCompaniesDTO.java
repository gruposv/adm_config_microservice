package com.gruposv.microservice_adm_and_config.modules.company.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ListCompaniesDTO(
        @PositiveOrZero(message = "O número da pagina deve ser positivo ou zero.")
        @NotNull(message = "O valor da página não pode ser nulo.")
        int page,

        @Positive(message = "O tamanho da página deve ser um valor positivo.")
        @NotNull(message = "O valor do tamanho da página não pode ser nulo.")
        int size
) {
}
