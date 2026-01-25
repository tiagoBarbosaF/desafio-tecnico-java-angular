package com.tiagobarbosa.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credito")
public class Credito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_credito", nullable = false)
    private String numeroCredito;
    @Column(name = "numero_nfse", nullable = false)
    private String numeroNfse;
    @Column(name = "data_constituicao", nullable = false)
    private LocalDate dataConstituicao;
    @Column(name = "valor_issqn", nullable = false)
    private BigDecimal valorIssqn;
    @Column(name = "tipo_credito", nullable = false)
    private String tipoCredito;
    @Column(name = "simples_nacional", nullable = false)
    private Boolean simplesNacional;
    @Column(name = "aliquota", nullable = false)
    private BigDecimal aliquota;
    @Column(name = "valor_faturado", nullable = false)
    private BigDecimal valorFaturado;
    @Column(name = "valor_deducao", nullable = false)
    private BigDecimal valorDeducao;
    @Column(name = "base_calculo", nullable = false)
    private BigDecimal baseCalculo;

    public Long getId() {
        return id;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public String getNumeroNfse() {
        return numeroNfse;
    }

    public LocalDate getDataConstituicao() {
        return dataConstituicao;
    }

    public BigDecimal getValorIssqn() {
        return valorIssqn;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public Boolean getSimplesNacional() {
        return simplesNacional;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public BigDecimal getValorFaturado() {
        return valorFaturado;
    }

    public BigDecimal getValorDeducao() {
        return valorDeducao;
    }

    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }
}
