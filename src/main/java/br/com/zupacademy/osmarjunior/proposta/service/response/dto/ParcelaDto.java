package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
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

    public static Set<Parcela> toParcelasList(Set<ParcelaDto> parcelas, Cartao cartao) {
        return parcelas.stream()
                .map(parcelaDto -> parcelaDto.toParcela(cartao))
                .collect(Collectors.toSet());
    }

    private Parcela toParcela(Cartao cartao) {
        return new Parcela(this.id, this.quantidade, this.valor, cartao);
    }

}
