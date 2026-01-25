package com.tiagobarbosa.backend.controller;

import com.tiagobarbosa.backend.dto.CreditoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Crédito",
        description = "Endpoints para consultas de créditos constituídos."
)
public interface CreditoControllerDoc {
    @Operation(
            summary = "Consultar créditos por número da NFS-e",
            description = "Retorna uma lista de créditos constituídos com base no número da NFS-e"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Créditos encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum crédito encontrado")
    })
    ResponseEntity<List<CreditoResponseDTO>> consultarPorNumeroNfse(
            @Parameter(
                    description = "Número identificador da NFS-e",
                    example = "7891011",
                    required = true
            )
            String numeroNfse
    );

    @Operation(
            summary = "Consultar crédito por número do crédito",
            description = "Retorna os detalhes de um crédito constituído específico"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Crédito encontrado"),
            @ApiResponse(responseCode = "404", description = "Crédito não encontrado")
    })
    ResponseEntity<CreditoResponseDTO> consultarPorNumeroCredito(
            @Parameter(
                    description = "Número identificador do crédito",
                    example = "123456",
                    required = true
            )
            String numeroCredito
    );
}
