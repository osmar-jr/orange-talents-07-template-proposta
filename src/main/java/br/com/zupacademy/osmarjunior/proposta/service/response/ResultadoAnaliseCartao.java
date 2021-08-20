package br.com.zupacademy.osmarjunior.proposta.service.response;

import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.service.response.dto.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ResultadoAnaliseCartao {

    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime emitidoEm;

    private String titular;
    private Set<BloqueioDto> bloqueios = new HashSet<>();
    private Set<AvisoDto> avisos = new HashSet<>();
    private Set<CarteiraDto> carteiras = new HashSet<>();
    private Set<ParcelaDto> parcelas = new HashSet<>();
    private BigDecimal limite;
    private RenegociacaoDto renegociacao;
    private VencimentoDto vencimento;
    private String idProposta;

    public ResultadoAnaliseCartao(String id,
                                  LocalDateTime emitidoEm,
                                  String titular,
                                  Set<BloqueioDto> bloqueios,
                                  Set<AvisoDto> avisos,
                                  Set<CarteiraDto> carteiras,
                                  Set<ParcelaDto> parcelas,
                                  BigDecimal limite,
                                  RenegociacaoDto renegociacao,
                                  VencimentoDto vencimento,
                                  String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public Cartao toCartao(Proposta proposta) {
        Cartao cartao = new Cartao(this.id, this.emitidoEm, this.limite, this.titular, proposta);

        atualizarCartao(cartao);
        return cartao;
    }

    private void atualizarCartao(Cartao cartao) {
        cartao.atualizaBloqueios(this.bloqueios);
        cartao.atualizaAvisos(this.avisos);
        cartao.atualizaCarteiras(this.carteiras);
        cartao.atualizaParcelas(this.parcelas);
        cartao.atualizaRenegociacoes(Optional.ofNullable(this.renegociacao));
        cartao.atualizaVencimento(Optional.ofNullable(this.vencimento));
    }
}
