package com.tiagobarbosa.backend.service.impl;

import com.tiagobarbosa.backend.dto.CreditoResponseDTO;
import com.tiagobarbosa.backend.exception.CreditoNotFoundException;
import com.tiagobarbosa.backend.mapper.CreditoMapper;
import com.tiagobarbosa.backend.repository.CreditoRepository;
import com.tiagobarbosa.backend.service.CreditoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;
    private final CreditoMapper creditoMapper;

    public CreditoServiceImpl(CreditoRepository creditoRepository, CreditoMapper creditoMapper) {
        this.creditoRepository = creditoRepository;
        this.creditoMapper = creditoMapper;
    }

    @Override
    public List<CreditoResponseDTO> consultarPorNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse).stream()
                .map(creditoMapper::toDTO).toList();
    }

    @Override
    public CreditoResponseDTO consultarPorNumeroCredito(String numeroCredito) {
        Objects.requireNonNull(numeroCredito, "numeroCredito não pode ser nulo.");
        return creditoRepository.findByNumeroCredito(numeroCredito).map(creditoMapper::toDTO)
                .orElseThrow(() -> new CreditoNotFoundException("Crédito " + numeroCredito + " não encontrado."));
    }
}
