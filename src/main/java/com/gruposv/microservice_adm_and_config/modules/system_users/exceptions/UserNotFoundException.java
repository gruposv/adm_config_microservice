package com.gruposv.microservice_adm_and_config.modules.system_users.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
