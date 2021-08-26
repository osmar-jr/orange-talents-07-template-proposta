package br.com.zupacademy.osmarjunior.proposta.controller.request;

import br.com.zupacademy.osmarjunior.proposta.annotations.CpfOrCnpj;
import br.com.zupacademy.osmarjunior.proposta.model.Endereco;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.model.utils.DocumentoLimpo;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @CpfOrCnpj
    private String cpfOuCnpj;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Positive
    private BigDecimal salario;

    @NotNull
    @Valid
    private EnderecoRequest endereco;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaPropostaRequest(@NotBlank String nome,
                               @NotBlank String cpfOuCnpj,
                               @NotBlank @Email String email,
                               @NotNull @Positive BigDecimal salario,
                               @NotNull @Valid EnderecoRequest endereco) {
        this.nome = nome;
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    /**
     * Concede get para serialização do objeto endereço ao Jackson
     */
    public EnderecoRequest getEndereco() {
        return endereco;
    }

    /**
     * Concede acesso ao validator de CPF ou CNPJ unico por proposta
     */
    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public Proposta toProposta() {
        Endereco novoEndereco = this.endereco.toEndereco();
        return new Proposta(this.nome, new DocumentoLimpo(this.cpfOuCnpj), this.email, this.salario, novoEndereco);
    }
}
