package com.gruposv.microservice_adm_and_config.modules.system_users.exceptions;

// Exceção para tratar valores que não podem ser duplicados no banco de dados.
public class UniqueDataException extends RuntimeException {
    public UniqueDataException(String message) {
        super(message);
    }
}
