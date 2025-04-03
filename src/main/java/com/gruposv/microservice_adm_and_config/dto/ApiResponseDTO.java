package com.gruposv.microservice_adm_and_config.dto;

import java.util.List;

public record ApiResponseDTO<T>(
   String status,
   int code,
   T data,
   String message,
   List<String> errors
) {}
