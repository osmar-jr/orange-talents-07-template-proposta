package br.com.zupacademy.osmarjunior.proposta.service.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;

public class SolicitacaoBloqueio {

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String sistemaResponsavel;

    @Deprecated
    public SolicitacaoBloqueio() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SolicitacaoBloqueio(@NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING) String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
