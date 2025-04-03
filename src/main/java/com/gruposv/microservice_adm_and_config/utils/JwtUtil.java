package com.gruposv.microservice_adm_and_config.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${api.security.token.secret}")
    private String secretKey;

    private final Algorithm algorithm;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtUtil(@Value("${api.security.token.secret}") String secretKey) {
        this.secretKey = secretKey;
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    public String generateToken(UserEntity user) {
        try {
            return JWT.create()
                    .withIssuer("gruposv_erp")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withClaim("roles", user.getRoles().stream().map(roleEntity -> roleEntity.getRoleName()).toList()) // Serializa lista para JSON
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(48)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toInstant();
    }

    public DecodedJWT returnTokenDecoded(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("gruposv_erp")
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String email = getUserEmail(token);
        return email.equals(userDetails.getUsername());
    }

    public String getUserEmail(String token) {
        return returnTokenDecoded(token).getSubject();
    }

    public Long getUserId(String token) {
        return returnTokenDecoded(token).getClaim("id").asLong();
    }

    public List<String> getUserRoles(String token) {
        try {
            String rolesJson = returnTokenDecoded(token).getClaim("roles").asString();
            return objectMapper.readValue(rolesJson, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao desserializar roles do token", e);
        }
    }

}

