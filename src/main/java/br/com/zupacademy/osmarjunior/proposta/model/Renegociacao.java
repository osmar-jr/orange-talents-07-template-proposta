package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String idRenegociacaoCartoesClient;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Renegociacao() {
    }

    public Renegociacao(String idRenegociacaoCartoesClient, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao, Cartao cartao) {
        this.idRenegociacaoCartoesClient = idRenegociacaoCartoesClient;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renegociacao that = (Renegociacao) o;
        return Objects.equals(id, that.id) && Objects.equals(idRenegociacaoCartoesClient, that.idRenegociacaoCartoesClient) && Objects.equals(quantidade, that.quantidade) && Objects.equals(valor, that.valor) && Objects.equals(dataDeCriacao, that.dataDeCriacao) && Objects.equals(cartao, that.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRenegociacaoCartoesClient, quantidade, valor, dataDeCriacao, cartao);
    }
}
