package br.com.zupacademy.osmarjunior.proposta.service.request;

import br.com.zupacademy.osmarjunior.proposta.annotations.ExistsId;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;

import javax.validation.constraints.NotBlank;

public class SolicitacaoAnalise {
    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    @ExistsId(classDomain = Proposta.class, attributeName = "id")
    private String idProposta;

    public SolicitacaoAnalise(@NotBlank String documento,
                              @NotBlank String nome,
                              @NotBlank String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
