package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Aviso;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;

import java.util.Set;
import java.util.stream.Collectors;

public class AvisoDto {

    private String validoAte;
    private String destino;

    public AvisoDto(String validoAte, String destino) {
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
