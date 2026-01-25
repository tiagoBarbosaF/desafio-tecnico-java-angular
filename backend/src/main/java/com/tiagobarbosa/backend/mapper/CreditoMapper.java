package com.tiagobarbosa.backend.mapper;

import com.tiagobarbosa.backend.dto.CreditoResponseDTO;
import com.tiagobarbosa.backend.entity.Credito;
import org.springframework.stereotype.Component;

@Component
public class CreditoMapper {

    public CreditoResponseDTO toDTO(Credito entity) {
        return new CreditoResponseDTO(
                entity.getNumeroCredito(),
                entity.getNumeroNfse(),
                entity.getDataConstituicao(),
                entity.getValorIssqn(),
                entity.getTipoCredito(),
                entity.getSimplesNacional(),
                entity.getAliquota(),
                entity.getValorFaturado(),
                entity.getValorDeducao(),
                entity.getBaseCalculo()
        );
    }
}
