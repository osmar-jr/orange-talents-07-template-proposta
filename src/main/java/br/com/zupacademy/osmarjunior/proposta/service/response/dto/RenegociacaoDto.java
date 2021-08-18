package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.model.Renegociacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoDto {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataDeCriacao;

    public RenegociacaoDto(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Renegociacao toRenegociacao(Proposta proposta) {
        return new Renegociacao(this.id, this.quantidade, this.valor, this.dataDeCriacao, proposta);
    }
}
