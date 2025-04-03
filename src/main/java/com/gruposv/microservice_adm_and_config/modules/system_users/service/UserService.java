package com.gruposv.microservice_adm_and_config.modules.system_users.service;

import com.gruposv.microservice_adm_and_config.config.security.CustomUserDetails;
import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.address.repository.AddressRepository;
import com.gruposv.microservice_adm_and_config.modules.address.service.AddressService;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.company.exception.BranchNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.company.repository.CompanyBranchRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.ListUsersDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.dto.UserDTO;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.RoleEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.entity.UserEntity;
import com.gruposv.microservice_adm_and_config.modules.system_users.enums.UserStatus;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.RoleNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UniqueDataException;
import com.gruposv.microservice_adm_and_config.modules.system_users.exceptions.UserNotFoundException;
import com.gruposv.microservice_adm_and_config.modules.system_users.mapper.UserDTOMapper;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.RoleRepository;
import com.gruposv.microservice_adm_and_config.modules.system_users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CompanyBranchRepository companyBranchRepository;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, RoleRepository roleRepository, CompanyBranchRepository companyBranchRepository, AddressService addressService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.companyBranchRepository = companyBranchRepository;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {

        // Verificação de CPF e email para não realizar cadastro de registros duplicados
        if(userDTO.getCpf() != null && this.userRepository.existsByCpf(userDTO.getCpf())) throw new UniqueDataException("Esse CPF já existe, portanto esse cadastro é inválido.");
        if(userDTO.getCpf() != null && this.userRepository.existsByEmail(userDTO.getEmail())) throw new UniqueDataException("Esse E-mail já existe, portanto esse cadastro é inválido.");

        // Mapeando usuário de cadastro para enrtidade
        UserEntity user = UserDTOMapper.toEntity(userDTO);
        AddressEntity address = addressService.create(user.getAddress());
        user.setAddress(address);
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));

        // Buscar A filial de cadastro
        CompanyBranchEntity branch = this.companyBranchRepository
                .findById(userDTO.getBranchId())
                .orElseThrow(() -> new BranchNotFoundException("A filial com o ID " + userDTO.getBranchId() + " Não foi encontrada"));
        user.setBranch(branch);

        // Buscar as roles
        List<RoleEntity> roles = this.roleRepository.findByRoleNameIn(userDTO.getRoleNames());
        if(roles.isEmpty()) throw new RoleNotFoundException("Nenhum cargo foi encontrado, portanto não é possível cadastrar o usuário.");
        user.setRoles(roles);

        user.setUserStatus(UserStatus.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return UserDTOMapper.toDTO(userRepository.save(user));
    }

    public UserDTO findById(Long id) {
        return userRepository
                .findById(id)
                .map(UserDTOMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("O Usuário com o ID: " + id + "Não foi encontrado."));
    }

    public Page<UserDTO> findAll(ListUsersDTO listUsersDTO){
        Pageable pageable = PageRequest.of(listUsersDTO.page(), listUsersDTO.size());
        Page<UserEntity> userEntities =  userRepository.findAll(pageable);
        return userEntities.map(UserDTOMapper::toDTO);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {

        if(userDTO.getCpf() != null && this.userRepository.existsByCpf(userDTO.getCpf())) throw new UniqueDataException("Esse CPF já existe, portanto esse cadastro é inválido.");
        if(userDTO.getCpf() != null && this.userRepository.existsByEmail(userDTO.getEmail())) throw new UniqueDataException("Esse E-mail já existe, portanto esse cadastro é inválido.");

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        // Atualiza apenas os campos informados no DTO
        UserEntity user = UserDTOMapper.toUpdateEntity(userDTO, userEntity);

        // Atualizando as Roles
        user.setRoles(this.roleRepository.findByRoleNameIn(userDTO.getRoleNames()));

        // Retorna o DTO atualizado após a transação
        user.setUpdatedAt(LocalDateTime.now());
        return UserDTOMapper.toDTO(this.userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado no processo de autenticação. Verifique e-mail ou senha."));

        return new CustomUserDetails(user);
    }

}
