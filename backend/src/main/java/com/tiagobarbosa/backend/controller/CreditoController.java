package com.tiagobarbosa.backend.controller;

import com.tiagobarbosa.backend.dto.CreditoResponseDTO;
import com.tiagobarbosa.backend.service.CreditoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController implements CreditoControllerDoc {

    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoResponseDTO>> consultarPorNumeroNfse(@PathVariable String numeroNfse) {
        return ResponseEntity.ok(creditoService.consultarPorNumeroNfse(numeroNfse));
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoResponseDTO> consultarPorNumeroCredito(@PathVariable String numeroCredito) {
        return ResponseEntity.ok(creditoService.consultarPorNumeroCredito(numeroCredito));
    }
}
