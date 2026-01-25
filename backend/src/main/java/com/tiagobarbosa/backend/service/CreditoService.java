package com.tiagobarbosa.backend.service;

import com.tiagobarbosa.backend.dto.CreditoResponseDTO;

import java.util.List;

public interface CreditoService {
    List<CreditoResponseDTO> consultarPorNumeroNfse(String numeroNfse);

    CreditoResponseDTO consultarPorNumeroCredito(String numeroCredito);
}
