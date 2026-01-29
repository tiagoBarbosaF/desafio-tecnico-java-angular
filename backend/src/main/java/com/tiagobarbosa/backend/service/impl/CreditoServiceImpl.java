package com.tiagobarbosa.backend.service.impl;

import com.tiagobarbosa.backend.dto.CreditoResponseDTO;
import com.tiagobarbosa.backend.exception.CreditoNotFoundException;
import com.tiagobarbosa.backend.infrastructure.messaging.ConsultaCreditoEvent;
import com.tiagobarbosa.backend.infrastructure.messaging.ConsultaCreditoEventPublisher;
import com.tiagobarbosa.backend.infrastructure.messaging.TipoConsulta;
import com.tiagobarbosa.backend.mapper.CreditoMapper;
import com.tiagobarbosa.backend.repository.CreditoRepository;
import com.tiagobarbosa.backend.service.CreditoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;
    private final CreditoMapper creditoMapper;
    private final ConsultaCreditoEventPublisher consultaCreditoEventPublisher;

    public CreditoServiceImpl(CreditoRepository creditoRepository, CreditoMapper creditoMapper,
                              ConsultaCreditoEventPublisher consultaCreditoEventPublisher) {
        this.creditoRepository = creditoRepository;
        this.creditoMapper = creditoMapper;
        this.consultaCreditoEventPublisher = consultaCreditoEventPublisher;
    }

    @Override
    public List<CreditoResponseDTO> consultarPorNumeroNfse(String numeroNfse) {
        List<CreditoResponseDTO> creditos = creditoRepository.findByNumeroNfse(numeroNfse).stream()
                .map(creditoMapper::toDTO).toList();

        ConsultaCreditoEvent event = new ConsultaCreditoEvent(
                TipoConsulta.NFS_E,
                numeroNfse,
                !creditos.isEmpty(),
                LocalDateTime.now()
        );

        consultaCreditoEventPublisher.publish(event);
        return creditos;
    }

    @Override
    public CreditoResponseDTO consultarPorNumeroCredito(String numeroCredito) {
        Objects.requireNonNull(numeroCredito, "numeroCredito não pode ser nulo.");
        Optional<CreditoResponseDTO> creditoResponseDTO = creditoRepository.findByNumeroCredito(numeroCredito)
                .map(creditoMapper::toDTO);

        ConsultaCreditoEvent event = new ConsultaCreditoEvent(
                TipoConsulta.NUMERO_CREDITO,
                numeroCredito,
                creditoResponseDTO.isPresent(),
                LocalDateTime.now()
        );

        consultaCreditoEventPublisher.publish(event);

        return creditoResponseDTO
                .orElseThrow(() -> new CreditoNotFoundException("Crédito " + numeroCredito + " não encontrado."));
    }
}
