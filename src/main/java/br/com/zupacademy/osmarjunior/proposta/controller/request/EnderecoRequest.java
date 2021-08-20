package br.com.zupacademy.osmarjunior.proposta.controller.request;

import br.com.zupacademy.osmarjunior.proposta.model.Endereco;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EnderecoRequest {

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

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EnderecoRequest(@NotBlank String rua,
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

    /**
     * Concede gets de todos os atributos para serialização do objeto endereço ao Jackson
     */
    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Endereco toEndereco() {
        return new Endereco(this.rua,
                this.numero,
                this.bairro,
                this.complemento,
                this.cidade,
                this.estado);
    }
}
