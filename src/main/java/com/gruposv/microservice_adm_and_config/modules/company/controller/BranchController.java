package com.gruposv.microservice_adm_and_config.modules.company.controller;

import com.gruposv.microservice_adm_and_config.dto.ApiResponseDTO;
import com.gruposv.microservice_adm_and_config.modules.company.dto.BranchDTO;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyBranchEntity;
import com.gruposv.microservice_adm_and_config.modules.company.service.CompanyBranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final CompanyBranchService branchService;

    public BranchController(CompanyBranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_BRANCHES')")
    public ResponseEntity<ApiResponseDTO> create(@RequestBody @Valid BranchDTO branchDTO){
        BranchDTO branch = this.branchService.create(branchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), branch, "Filial cadastrada com sucesso.", List.of()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_BRANCHES')")
    public ResponseEntity<ApiResponseDTO> findAll(){
        List<BranchDTO> branches = this.branchService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), branches, "Listagem de filiais", List.of()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_BRANCHES')")
    public ResponseEntity<ApiResponseDTO> findById(@PathVariable Long id){
        BranchDTO branch = this.branchService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), branch, "Filial retornada com sucesso.", List.of()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_BRANCHES')")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Long id, @RequestBody BranchDTO branchDTO){
        BranchDTO branch = this.branchService.update(id, branchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), branch, "Filial editada com sucesso.", List.of()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_BRANCHES')")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Long id){
        this.branchService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), null, "Filial excluída com sucesso.", List.of()));
    }

}
