package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class Endereco {

    @NotBlank
    private String rua;

    @NotNull @Positive
    private Integer numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @Deprecated
    public Endereco() {
    }

    public Endereco(@NotBlank String rua,
                    @NotNull @Positive Integer numero,
                    @NotBlank String bairro,
                    @NotBlank String complemento,
                    @NotBlank String cidade,
                    @NotBlank String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
