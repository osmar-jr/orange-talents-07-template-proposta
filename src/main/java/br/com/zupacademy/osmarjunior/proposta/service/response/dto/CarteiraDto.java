package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Carteira;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class CarteiraDto {

    private String id;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime associadaEm;
    private String emissor;

    public CarteiraDto(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public static Set<Carteira> toCarteirasList(Set<CarteiraDto> carteiras, Proposta proposta) {
        return carteiras.stream()
                .map(carteiraDto -> carteiraDto.toCarteira(proposta))
                .collect(Collectors.toSet());
    }

    private Carteira toCarteira(Proposta proposta) {
        return new Carteira(this.id, this.email, this.associadaEm, this.emissor, proposta);
    }
}
