package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Aviso;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class AvisoDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;
    private String destino;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoDto(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public static Set<Aviso> toAvisosList(Set<AvisoDto> avisos, Cartao cartao) {
        return avisos.stream()
                .map(avisoDto -> avisoDto.toAviso(cartao))
                .collect(Collectors.toSet());
    }

    private Aviso toAviso(Cartao cartao){
        return new Aviso(this.validoAte, this.destino, cartao);
    }

}
