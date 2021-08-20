package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String idParcelaCartoesClient;
    private Integer quantidade;
    private BigDecimal valor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Parcela() {
    }

    public Parcela(String idParcelaCartoesClient, Integer quantidade, BigDecimal valor, Cartao cartao) {
        this.idParcelaCartoesClient = idParcelaCartoesClient;
        this.quantidade = quantidade;
        this.valor = valor;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return Objects.equals(id, parcela.id) && Objects.equals(idParcelaCartoesClient, parcela.idParcelaCartoesClient) && Objects.equals(quantidade, parcela.quantidade) && Objects.equals(valor, parcela.valor) && Objects.equals(cartao, parcela.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idParcelaCartoesClient, quantidade, valor, cartao);
    }
}
