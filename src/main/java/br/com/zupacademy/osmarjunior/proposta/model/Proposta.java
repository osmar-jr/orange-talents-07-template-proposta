package br.com.zupacademy.osmarjunior.proposta.model;

import br.com.zupacademy.osmarjunior.proposta.controller.response.PropostaResponse;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;
import br.com.zupacademy.osmarjunior.proposta.model.utils.DocumentoLimpo;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String cpfOuCnpj;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Positive
    private BigDecimal salario;

    @NotNull
    @Valid
    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String nome,
                    @NotNull @Valid DocumentoLimpo documentoLimpo,
                    @NotBlank @Email String email,
                    @NotNull @Positive BigDecimal salario,
                    @NotNull @Valid Endereco endereco) {
        this.nome = nome;
        this.cpfOuCnpj = documentoLimpo.hash();
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    /**
     * Concede o ID serial de proposta para criação do link de acesso.
     */
    public String getId() {
        return id;
    }

    public SolicitacaoAnalise toSolicitacaoAnalise() {
        return new SolicitacaoAnalise(this.cpfOuCnpj, this.nome, this.id);
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public PropostaResponse toResponse() {
        return new PropostaResponse(this.id, this.nome, this.statusProposta);
    }

    public void atualizaCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
