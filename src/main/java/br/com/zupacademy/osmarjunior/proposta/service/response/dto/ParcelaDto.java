package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Parcela;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ParcelaDto {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public ParcelaDto(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public static Set<Parcela> toParcelasList(Set<ParcelaDto> parcelas, Proposta proposta) {
        return parcelas.stream()
                .map(parcelaDto -> parcelaDto.toParcela(proposta))
                .collect(Collectors.toSet());
    }

    private Parcela toParcela(Proposta proposta) {
        return new Parcela(this.id, this.quantidade, this.valor, proposta);
    }

}
