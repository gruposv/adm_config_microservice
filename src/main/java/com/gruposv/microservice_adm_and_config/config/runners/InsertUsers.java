package com.gruposv.microservice_adm_and_config.config.runners;

import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyBranchRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.UserEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.enums.UserStatus;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.RoleRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(5)
public class InsertUsers implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CompanyBranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    public InsertUsers(UserRepository userRepository, RoleRepository roleRepository, CompanyBranchRepository branchRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        RoleEntity adm = this.roleRepository.findByRoleName("ADM").orElseThrow(() -> new RuntimeException("Não foi possível encontrar o cargo ADM"));
        RoleEntity comum = this.roleRepository.findByRoleName("COMUM").orElseThrow(() -> new RuntimeException("Não foi possível encontrar o cargo COMUM"));

        if(this.userRepository.findAll().isEmpty()){
            List<UserEntity> users = new ArrayList<>();

            // Buscar filial para cadastrar usuário
            List<CompanyBranchEntity> branches = this.branchRepository.findAll();
            if(branches.isEmpty()) throw new RuntimeException("Não foi possível realizar os cadastros dos usuários pois não existem filiais no banco de dados.");

            users.add(new UserEntity(null, "Monkey D. Luffy", "52641999064", "luffy@email.com", passwordEncoder.encode("Luffy123"), UserStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now(), null, List.of(adm), branches.getFirst()));
            users.add(new UserEntity(null, "Naruto Uzumaki", "71814407057", "naruto@email.com", passwordEncoder.encode("Naruto123"), UserStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now(), null, List.of(comum), branches.getFirst()));
            users.add(new UserEntity(null, "Son Goku", "28728633032", "goku@email.com", passwordEncoder.encode("Goku123"), UserStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now(), null, List.of(adm), branches.getFirst()));
            users.add(new UserEntity(null, "Gon Freecs", "73111895009", "gon@email.com", passwordEncoder.encode("Gon123"), UserStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now(), null, List.of(comum), branches.getFirst()));

            this.userRepository.saveAll(users);
        }

    }

}
