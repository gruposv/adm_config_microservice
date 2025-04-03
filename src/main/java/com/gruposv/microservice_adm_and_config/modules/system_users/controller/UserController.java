package com.gruposv.microservice_adm_and_config.modules.system_users.controller;

import com.gruposv.microservice_adm_and_config.dto.ApiResponseDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.ListUsersDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.UserDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<ApiResponseDTO<UserDTO>> createUser (@RequestBody @Valid UserDTO userDTO){
        UserDTO user = userService.create(userDTO);

        ApiResponseDTO<UserDTO> response = new ApiResponseDTO(
                "success",
                HttpStatus.CREATED.value(),
                user,
                "Novo usuário criado com sucesso!",
                List.of()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public ResponseEntity<ApiResponseDTO<Page<UserDTO>>> listUsers (@ModelAttribute @Valid ListUsersDTO listUsersDTO) {
        Page<UserDTO> users = userService.findAll(listUsersDTO);
        ApiResponseDTO<Page<UserDTO>> response = new ApiResponseDTO(
                "success",
                HttpStatus.OK.value(),
                users,
                "Usuários retornados com sucesso!",
                List.of()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public ResponseEntity<ApiResponseDTO<UserDTO>> findUserById (@PathVariable Long id){
        UserDTO userDTO = userService.findById(id);
        ApiResponseDTO<UserDTO> response = new ApiResponseDTO<>(
                "success",
                HttpStatus.OK.value(),
                userDTO,
                "Usuário encontrado!",
                List.of()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<ApiResponseDTO<UserDTO>> updateUser (@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.update(id, userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(
                "success",
                HttpStatus.OK.value(),
                user,
                "Usuário atualizado",
                List.of()
        ));
    }


}
