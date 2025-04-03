package com.gruposv.microservice_adm_and_config.modules.system_users.exceptions;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(String message) {
        super(message);
    }
}
