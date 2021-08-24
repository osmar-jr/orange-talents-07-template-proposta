package br.com.zupacademy.osmarjunior.proposta.model;

import br.com.zupacademy.osmarjunior.proposta.service.response.dto.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titular;
    private String numeroCartao;
    private LocalDateTime cartaoEmitidoEm;
    private BigDecimal limiteCartao;

    @OneToOne
    private Proposta proposta;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Renegociacao> renegociacoes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Vencimento> vencimentos = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Bloqueio> bloqueios = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Aviso> avisos =  new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Carteira> carteiras = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Parcela> parcelas = new HashSet<>();


    @Deprecated
    public Cartao() {
    }

    public Cartao(String numeroCartao, LocalDateTime cartaoEmitidoEm, BigDecimal limiteCartao, String titular, Proposta proposta) {
        this.numeroCartao = numeroCartao;
        this.cartaoEmitidoEm = cartaoEmitidoEm;
        this.limiteCartao = limiteCartao;
        this.titular = titular;
        this.proposta = proposta;
    }

    public void atualizaBloqueios(Set<BloqueioDto> bloqueios) {
        Set<Bloqueio> novosBloqueios = BloqueioDto.toBloqueiosList(bloqueios, this);
        this.bloqueios.addAll(novosBloqueios);
    }

    public void atualizaAvisos(Set<AvisoDto> avisos) {
        Set<Aviso> novosAvisos = AvisoDto.toAvisosList(avisos, this);
        this.avisos.addAll(novosAvisos);
    }

    public void atualizaCarteiras(Set<CarteiraDto> carteiras) {
        Set<Carteira> novasCarteiras = CarteiraDto.toCarteirasList(carteiras, this);
        this.carteiras.addAll(novasCarteiras);
    }

    public void atualizaParcelas(Set<ParcelaDto> parcelas) {
        Set<Parcela> novasParcelas = ParcelaDto.toParcelasList(parcelas, this);
        this.parcelas.addAll(novasParcelas);
    }

    public void atualizaRenegociacoes(Optional<RenegociacaoDto> optionalRenegociacaoDto) {
        if(optionalRenegociacaoDto.isPresent()){
            RenegociacaoDto renegociacaoDto = optionalRenegociacaoDto.get();
            this.renegociacoes.add(renegociacaoDto.toRenegociacao(this));
        }
    }

    public void atualizaVencimento(Optional<VencimentoDto> optionalVencimentoDto) {
        if(optionalVencimentoDto.isPresent()){
            VencimentoDto vencimentoDto = optionalVencimentoDto.get();
            this.vencimentos.add(vencimentoDto.toVencimento(this));
        }
    }

    public Long getId() {
        return id;
    }
}
