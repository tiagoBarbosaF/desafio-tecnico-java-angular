package com.tiagobarbosa.backend.infrastructure.messaging;

import java.time.LocalDateTime;

public class ConsultaCreditoEvent {
    private final TipoConsulta tipoConsulta;
    private final String valorConsultado;
    private final boolean encontrado;
    private final LocalDateTime timestamp;

    public ConsultaCreditoEvent(TipoConsulta tipoConsulta, String valorConsultado, boolean encontrado,
                                LocalDateTime timestamp) {
        this.tipoConsulta = tipoConsulta;
        this.valorConsultado = valorConsultado;
        this.encontrado = encontrado;
        this.timestamp = timestamp;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public String getValorConsultado() {
        return valorConsultado;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
