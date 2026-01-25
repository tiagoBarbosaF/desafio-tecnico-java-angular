package com.tiagobarbosa.backend.controller;


import com.tiagobarbosa.backend.dto.CreditoResponseDTO;
import com.tiagobarbosa.backend.exception.GlobalExceptionHandler;
import com.tiagobarbosa.backend.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreditoControllerTest {
    private MockMvc mockMvc;
    private CreditoService creditoService;
    private CreditoController creditoController;

    @BeforeEach
    void setUp() {
        creditoService = mock(CreditoService.class);
        creditoController = new CreditoController(creditoService);

        mockMvc = MockMvcBuilders.standaloneSetup(creditoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testGetCreditosByNumeroNfse_Success() throws Exception {
        CreditoResponseDTO dto1 = new CreditoResponseDTO(
                "123", "7891011", null, null, null, false, null, null, null, null
        );
        CreditoResponseDTO dto2 = new CreditoResponseDTO(
                "456", "7891011", null, null, null, false, null, null, null, null
        );

        List<CreditoResponseDTO> lista = List.of(dto1, dto2);
        when(creditoService.consultarPorNumeroNfse("7891011")).thenReturn(lista);

        mockMvc.perform(get("/api/creditos/7891011"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].numeroCredito").value("123"))
                .andExpect(jsonPath("$[1].numeroCredito").value("456"));

        verify(creditoService, times(1)).consultarPorNumeroNfse("7891011");
    }

    @Test
    void testGetCreditoByNumeroCredito_Success() throws Exception {
        CreditoResponseDTO dto = new CreditoResponseDTO("123456", "7891011",
                null, null, null, false, null,
                null, null, null);
        when(creditoService.consultarPorNumeroCredito("123456")).thenReturn(dto);

        mockMvc.perform(get("/api/creditos/credito/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value("123456"));

        verify(creditoService, times(1)).consultarPorNumeroCredito("123456");
    }

    @Test
    void testGetCreditosByNumeroNfse_EmptyList() throws Exception {
        when(creditoService.consultarPorNumeroNfse("0000")).thenReturn(List.of());

        mockMvc.perform(get("/api/creditos/0000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(creditoService, times(1)).consultarPorNumeroNfse("0000");
    }
}