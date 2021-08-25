package br.com.zupacademy.osmarjunior.proposta.service.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SolicitacaoCarteira {

    @NotBlank @Email
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String email;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String carteira;

    @Deprecated
    public SolicitacaoCarteira() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SolicitacaoCarteira(@NotBlank @Email @JsonFormat(shape = JsonFormat.Shape.STRING) String email,
                               @NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING) String carteira) {
        this.email = email;
        this.carteira = carteira;
    }
}
