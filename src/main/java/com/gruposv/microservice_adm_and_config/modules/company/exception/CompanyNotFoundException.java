package com.gruposv.microservice_adm_and_config.modules.company.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
