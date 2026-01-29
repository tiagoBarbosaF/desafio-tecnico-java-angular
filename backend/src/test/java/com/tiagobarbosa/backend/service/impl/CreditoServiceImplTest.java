package com.tiagobarbosa.backend.service.impl;

import com.tiagobarbosa.backend.dto.CreditoResponseDTO;
import com.tiagobarbosa.backend.entity.Credito;
import com.tiagobarbosa.backend.exception.CreditoNotFoundException;
import com.tiagobarbosa.backend.infrastructure.messaging.ConsultaCreditoEvent;
import com.tiagobarbosa.backend.infrastructure.messaging.ConsultaCreditoEventPublisher;
import com.tiagobarbosa.backend.mapper.CreditoMapper;
import com.tiagobarbosa.backend.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditoServiceImplTest {
    @Mock
    private CreditoRepository creditoRepository;
    @Mock
    private CreditoMapper creditoMapper;
    @Mock
    private ConsultaCreditoEventPublisher consultaCreditoEventPublisher;
    @InjectMocks
    private CreditoServiceImpl creditoServiceImpl;

    @Test
    void testConsultarPorNumeroCredito_RetornaDTO() {
        Credito credito = mock(Credito.class);

        CreditoResponseDTO dto = new CreditoResponseDTO(
                "123456",
                "7891011",
                null,
                null,
                null,
                false,
                null,
                null,
                null,
                null
        );

        when(creditoRepository.findByNumeroCredito("123456")).thenReturn(Optional.of(credito));
        when(creditoMapper.toDTO(credito)).thenReturn(dto);

        CreditoResponseDTO result = creditoServiceImpl.consultarPorNumeroCredito("123456");

        assertNotNull(result);
        assertEquals("123456", result.numeroCredito());
        verify(creditoRepository, times(1)).findByNumeroCredito("123456");
        verify(creditoMapper, times(1)).toDTO(credito);
        verify(consultaCreditoEventPublisher, times(1)).publish(any(ConsultaCreditoEvent.class));
    }

    @Test
    void testConsultarPorNumeroCredito_NaoEncontrado() {
        when(creditoRepository.findByNumeroCredito("999999")).thenReturn(Optional.empty());

        assertThrows(CreditoNotFoundException.class, () ->
                creditoServiceImpl.consultarPorNumeroCredito("999999"));

        verify(creditoRepository, times(1)).findByNumeroCredito("999999");
        verifyNoInteractions(creditoMapper);
        verify(consultaCreditoEventPublisher, times(1)).publish(any(ConsultaCreditoEvent.class));
    }

    @Test
    void testConsultarPorNumeroNfse_RetornaListaDTOs() {
        Credito credito1 = mock(Credito.class);
        Credito credito2 = mock(Credito.class);

        CreditoResponseDTO dto1 = new CreditoResponseDTO("123", "7891011", null,
                null, null, false, null, null, null,
                null);
        CreditoResponseDTO dto2 = new CreditoResponseDTO("456", "7891011", null,
                null, null, false, null, null, null,
                null);

        when(creditoRepository.findByNumeroNfse("7891011")).thenReturn(List.of(credito1, credito2));
        when(creditoMapper.toDTO(credito1)).thenReturn(dto1);
        when(creditoMapper.toDTO(credito2)).thenReturn(dto2);

        List<CreditoResponseDTO> result = creditoServiceImpl.consultarPorNumeroNfse("7891011");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("123", result.get(0).numeroCredito());
        assertEquals("456", result.get(1).numeroCredito());
        verify(creditoRepository, times(1)).findByNumeroNfse("7891011");
        verify(creditoMapper, times(1)).toDTO(credito1);
        verify(creditoMapper, times(1)).toDTO(credito2);
        verify(consultaCreditoEventPublisher, times(1)).publish(any(ConsultaCreditoEvent.class));
    }

    @Test
    void testConsultarPorNumeroNfse_ListaVazia() {
        when(creditoRepository.findByNumeroNfse("0000")).thenReturn(List.of());

        List<CreditoResponseDTO> result = creditoServiceImpl.consultarPorNumeroNfse("0000");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(creditoRepository, times(1)).findByNumeroNfse("0000");
        verifyNoInteractions(creditoMapper);
        verify(consultaCreditoEventPublisher, times(1)).publish(any(ConsultaCreditoEvent.class));
    }
}