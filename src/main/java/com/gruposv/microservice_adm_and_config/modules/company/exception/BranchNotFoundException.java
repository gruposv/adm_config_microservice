package com.gruposv.microservice_adm_and_config.modules.company.exception;

public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(String message) {
        super(message);
    }
}
