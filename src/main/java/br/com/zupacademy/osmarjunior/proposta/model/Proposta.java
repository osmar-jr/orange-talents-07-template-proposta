package br.com.zupacademy.osmarjunior.proposta.model;

import br.com.zupacademy.osmarjunior.proposta.annotations.CpfOrCnpj;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_proposta")
public class Proposta {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @NotBlank
    private String nome;

    @NotBlank @CpfOrCnpj
    @Column(unique = true)
    private String cpfOuCnpj;

    @NotBlank @Email
    private String email;

    @NotNull @Positive
    private BigDecimal salario;

    @NotNull @Valid @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String nome,
                    @NotBlank String cpfOuCnpj,
                    @NotBlank @Email String email,
                    @NotNull @Positive BigDecimal salario,
                    @NotNull @Valid Endereco endereco) {
        this.nome = nome;
        this.cpfOuCnpj = cpfOuCnpj;
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
}
