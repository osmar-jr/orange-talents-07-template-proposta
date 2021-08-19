package br.com.zupacademy.osmarjunior.proposta.model;

import br.com.zupacademy.osmarjunior.proposta.annotations.CpfOrCnpj;
import br.com.zupacademy.osmarjunior.proposta.controller.response.PropostaResponse;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import br.com.zupacademy.osmarjunior.proposta.service.response.dto.RenegociacaoDto;
import br.com.zupacademy.osmarjunior.proposta.service.response.dto.VencimentoDto;
import ch.qos.logback.core.util.COWArrayList;
import com.zaxxer.hikari.util.ConcurrentBag;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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

    private String numeroCartao;
    private LocalDateTime cartaoEmitidoEm;
    private BigDecimal limiteCartao;

    @OneToMany(mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Set<Bloqueio> bloqueios = new HashSet<>();

    @OneToMany(mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Set<Aviso> avisos;

    @OneToMany(mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Set<Carteira> carteiras = new HashSet<>();

    @OneToMany(mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Set<Parcela> parcelas = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Set<Renegociacao> renegociacoes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Set<Vencimento> vencimentos = new HashSet<>();

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

    public void atualizaDadosDeCartao(String cartaoId,
                                      LocalDateTime cartaoEmitidoEm,
                                      BigDecimal limite, Set<Bloqueio> bloqueios,
                                      Set<Aviso> avisos,
                                      Set<Carteira> carteiras,
                                      Set<Parcela> parcelas) {
        this.numeroCartao = cartaoId;
        this.cartaoEmitidoEm = cartaoEmitidoEm;
        this.limiteCartao = limite;
        this.bloqueios.addAll(bloqueios);
        this.avisos.addAll(avisos);
        this.carteiras.addAll(carteiras);
        this.parcelas = parcelas;;
    }

    public void atualizaVencimento(Optional<VencimentoDto> vencimento) {
        vencimento.ifPresent(vencimentoDto -> this.vencimentos.add(vencimentoDto.toVencimento(this)));
    }

    public void atualizaRenegociacao(Optional<RenegociacaoDto> renegociacao) {
        renegociacao.ifPresent(renegociacaoDto -> this.renegociacoes.add(renegociacaoDto.toRenegociacao(this)));
    }

    public PropostaResponse toResponse() {
        return new PropostaResponse(this.id, this.nome, this.statusProposta);
    }
}
