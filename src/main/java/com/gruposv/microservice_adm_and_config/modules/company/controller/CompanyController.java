package com.gruposv.microservice_adm_and_config.modules.company.controller;

import com.gruposv.microservice_adm_and_config.dto.ApiResponseDTO;
import com.gruposv.microservice_adm_and_config.modules.company.dto.CompanyDTO;
import com.gruposv.microservice_adm_and_config.modules.company.dto.ListCompaniesDTO;
import com.gruposv.microservice_adm_and_config.modules.company.entity.CompanyEntity;
import com.gruposv.microservice_adm_and_config.modules.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_COMPANIES')")
    public ResponseEntity<ApiResponseDTO<CompanyDTO>> create(@RequestBody CompanyDTO companyDTO){
        CompanyDTO company = this.companyService.create(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<CompanyDTO>(
                "success",
                HttpStatus.CREATED.value(),
                company,
                "Empresa criada com sucesso.",
                List.of()
        ));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_COMPANIES')")
    public ResponseEntity<ApiResponseDTO<Page<CompanyDTO>>> findAll(@ModelAttribute @Valid ListCompaniesDTO listCompaniesDTO){
        Page<CompanyDTO> companies = this.companyService.findAll(listCompaniesDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), companies, "Empresas retornadas com sucesso.", List.of()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_COMPANIES')")
    public ResponseEntity<ApiResponseDTO<CompanyDTO>> findById(@PathVariable Long id){
        CompanyDTO company = this.companyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), company, "Empresa retornada com sucesso.", List.of()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_COMPANIES')")
    public ResponseEntity<ApiResponseDTO<CompanyDTO>> update(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO company = this.companyService.update(id, companyDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), company, "Atualização feita com sucesso.", List.of()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_COMPANIES')")
    public ResponseEntity<ApiResponseDTO<Void>> delete(@PathVariable Long id){
        this.companyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseDTO<>("success", HttpStatus.NO_CONTENT.value(), null, "Empresa deletada com sucesso.", List.of()));
    }

}
