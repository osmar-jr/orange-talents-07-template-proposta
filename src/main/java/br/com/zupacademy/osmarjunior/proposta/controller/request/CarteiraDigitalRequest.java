package br.com.zupacademy.osmarjunior.proposta.controller.request;

import br.com.zupacademy.osmarjunior.proposta.model.enums.Emissor;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoCarteira;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotBlank @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Emissor emissor;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CarteiraDigitalRequest(@NotBlank @Email String email,
                                  @NotBlank Emissor emissor) {
        this.email = email;
        this.emissor = emissor;
    }

    public SolicitacaoCarteira toSolicitacaoCarteira() {
        return new SolicitacaoCarteira(this.email, this.emissor.toString());
    }

    public String getEmail() {
        return email;
    }

    public String getValorEmissor() {
        return emissor.toString();
    }
}
