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

    private String idRenegociacaoDoClient;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @ManyToOne
    private Proposta proposta;

    @Deprecated
    public Renegociacao() {
    }

    public Renegociacao(String idRenegociacaoDoClient, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao, Proposta proposta) {

        this.idRenegociacaoDoClient = idRenegociacaoDoClient;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
        this.proposta = proposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renegociacao that = (Renegociacao) o;
        return Objects.equals(id, that.id) && Objects.equals(idRenegociacaoDoClient, that.idRenegociacaoDoClient) && Objects.equals(quantidade, that.quantidade) && Objects.equals(valor, that.valor) && Objects.equals(dataDeCriacao, that.dataDeCriacao) && Objects.equals(proposta, that.proposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRenegociacaoDoClient, quantidade, valor, dataDeCriacao, proposta);
    }
}
