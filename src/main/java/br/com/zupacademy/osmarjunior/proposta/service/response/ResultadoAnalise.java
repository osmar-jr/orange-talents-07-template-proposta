package br.com.zupacademy.osmarjunior.proposta.service.response;

import br.com.zupacademy.osmarjunior.proposta.model.enums.ResultadoSolicitacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ResultadoAnalise {

    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    @NotNull
    private ResultadoSolicitacao resultadoSolicitacao;

    public ResultadoAnalise(@NotBlank String documento,
                            @NotBlank String nome,
                            @NotBlank String idProposta,
                            @NotNull ResultadoSolicitacao resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
