package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Aviso;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;

import java.util.Set;
import java.util.stream.Collectors;

public class AvisoDto {

    private String validoAte;
    private String destino;

    public AvisoDto(String validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public static Set<Aviso> toAvisosList(Set<AvisoDto> avisos, Proposta proposta) {
        return avisos.stream()
                .map(avisoDto -> avisoDto.toAviso(proposta))
                .collect(Collectors.toSet());
    }

    public Aviso toAviso(Proposta proposta){
        return new Aviso(this.validoAte, this.destino, proposta);
    }

}
