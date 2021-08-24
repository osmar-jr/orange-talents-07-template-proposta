package br.com.zupacademy.osmarjunior.proposta.service.request;

import javax.validation.constraints.NotBlank;

public class SolicitacaoBloqueio {

    @NotBlank
    private String sistemaResponsavel;

    public SolicitacaoBloqueio(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    /**
     * Setter para de/seriazalizacao no sistema legado
     */
    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
