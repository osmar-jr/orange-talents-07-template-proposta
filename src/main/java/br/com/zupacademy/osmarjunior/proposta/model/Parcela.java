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

    private String idParcelaDoClient;
    private Integer quantidade;
    private BigDecimal valor;

    @ManyToOne
    private Proposta proposta;

    @Deprecated
    public Parcela() {
    }

    public Parcela(String idParcelaDoClient, Integer quantidade, BigDecimal valor, Proposta proposta) {

        this.idParcelaDoClient = idParcelaDoClient;
        this.quantidade = quantidade;
        this.valor = valor;
        this.proposta = proposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return Objects.equals(id, parcela.id) && Objects.equals(idParcelaDoClient, parcela.idParcelaDoClient) && Objects.equals(quantidade, parcela.quantidade) && Objects.equals(valor, parcela.valor) && Objects.equals(proposta, parcela.proposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idParcelaDoClient, quantidade, valor, proposta);
    }
}
