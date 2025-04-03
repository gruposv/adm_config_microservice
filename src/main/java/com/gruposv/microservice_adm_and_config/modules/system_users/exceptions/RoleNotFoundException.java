package com.gruposv.microservice_adm_and_config.modules.system_users.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
