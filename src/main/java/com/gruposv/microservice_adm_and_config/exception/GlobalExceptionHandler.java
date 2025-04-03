package com.gruposv.microservice_adm_and_config.exception;

import com.gruposv.microservice_adm_and_config.dto.ApiResponseDTO;
import com.gruposv.microservice_adm_and_config.modules.company.exception.BranchNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.company.exception.CompanyNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.PermissionNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.RoleNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UniqueDataException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>("error", HttpStatus.NOT_FOUND.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(UniqueDataException.class)
    public ResponseEntity handleUniqueDataException(UniqueDataException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponseDTO<>("error", HttpStatus.CONFLICT.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity handleRoleNotFoundException(RoleNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>("error", HttpStatus.NOT_FOUND.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(PermissionNotFoundException.class)
    public ResponseEntity handlePermissionNotFoundException(PermissionNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>("error", HttpStatus.NOT_FOUND.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity handleCompanyNotFoundException(CompanyNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>("error", HttpStatus.NOT_FOUND.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity handleBranchNotFoundException(BranchNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>("error", HttpStatus.NOT_FOUND.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseDTO<>("error", HttpStatus.UNAUTHORIZED.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseDTO<>("error", HttpStatus.UNAUTHORIZED.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleLockedException(LockedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponseDTO<>("error", HttpStatus.FORBIDDEN.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleDisabledException(DisabledException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponseDTO<>("error", HttpStatus.FORBIDDEN.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleAccountExpiredException(AccountExpiredException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponseDTO<>("error", HttpStatus.FORBIDDEN.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleCredentialsExpiredException(CredentialsExpiredException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponseDTO<>("error", HttpStatus.FORBIDDEN.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponseDTO<>("error", HttpStatus.FORBIDDEN.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseDTO<>("error", HttpStatus.UNAUTHORIZED.value(), null, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDTO<>("error", HttpStatus.BAD_REQUEST.value(), errors, ex.getMessage(), List.of()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseDTO<>("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), null, ex.getMessage(), List.of()));
    }

}
