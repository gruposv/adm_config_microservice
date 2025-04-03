package com.gruposv.microservice_adm_and_config.modules.system_users.controller;

import com.gruposv.microservice_adm_and_config.dto.ApiResponseDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.LoginDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.UserEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.UserRepository;
import com.gruposv.microservice_adm_and_config.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<String>> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password())
        );

        UserEntity user = userRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new BadCredentialsException("Erro na autenticação, verifique se o e-mail ou senha estão corretos."));

        String token = jwtUtil.generateToken(user);

        ApiResponseDTO<String> response = new ApiResponseDTO<>(
                "success",
                HttpStatus.OK.value(),
                token,
                "Usuário autenticado com sucesso!",
                List.of()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
