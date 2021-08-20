package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Vencimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String idVencimentoCartoesClient;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String idVencimentoCartoesClient, Integer dia, LocalDateTime dataDeCriacao, Cartao cartao) {
        this.idVencimentoCartoesClient = idVencimentoCartoesClient;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vencimento that = (Vencimento) o;
        return Objects.equals(id, that.id) && Objects.equals(idVencimentoCartoesClient, that.idVencimentoCartoesClient) && Objects.equals(dia, that.dia) && Objects.equals(dataDeCriacao, that.dataDeCriacao) && Objects.equals(cartao, that.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVencimentoCartoesClient, dia, dataDeCriacao, cartao);
    }
}
