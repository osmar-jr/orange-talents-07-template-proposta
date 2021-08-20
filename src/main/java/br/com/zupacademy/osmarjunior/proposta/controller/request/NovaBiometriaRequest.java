package br.com.zupacademy.osmarjunior.proposta.controller.request;

import br.com.zupacademy.osmarjunior.proposta.annotations.IsBase64;
import br.com.zupacademy.osmarjunior.proposta.model.Biometria;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaBiometriaRequest {

    @NotBlank
    @IsBase64
    private String biometria;

    public NovaBiometriaRequest(@NotBlank String biometria) {
        Base64.isBase64(biometria);
        this.biometria = biometria;
    }

    public Biometria toBiometria(@NotNull Cartao cartao) {
        return new Biometria(this.biometria, cartao);
    }
}
