package com.tiagobarbosa.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Dados do crédito constituído")
public record CreditoResponseDTO(
        @Schema(example = "123456")
        String numeroCredito,
        @Schema(example = "782394")
        String numeroNfse,
        @Schema(example = "2026-01-01")
        LocalDate dataConstituicao,
        @Schema(example = "1500.75")
        BigDecimal valorIssqn,
        @Schema(example = "ISSQN")
        String tipoCredito,
        @Schema(example = "true")
        Boolean simplesNacional,
        @Schema(example = "5.0")
        BigDecimal aliquota,
        @Schema(example = "5000.00")
        BigDecimal valorFaturado,
        @Schema(example = "30000.00")
        BigDecimal valorDeducao,
        @Schema(example = "20000.00")
        BigDecimal baseCalculo
) {
}
