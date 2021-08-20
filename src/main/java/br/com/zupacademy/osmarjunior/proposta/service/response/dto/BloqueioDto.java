package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Bloqueio;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class BloqueioDto {

    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;
    private Boolean ativo;

    public BloqueioDto(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public static Set<Bloqueio> toBloqueiosList(Set<BloqueioDto> bloqueios, Cartao cartao) {
        return bloqueios
                .stream()
                .map(bloqueioDto -> bloqueioDto.toBloqueio(cartao))
                .collect(Collectors.toSet());
    }


    private Bloqueio toBloqueio(Cartao cartao){
        return new Bloqueio(this.id, this.bloqueadoEm, this.sistemaResponsavel, this.ativo, cartao);
    }
}
