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

    private String idVencimentoDoClient;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @ManyToOne
    private Proposta proposta;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String idVencimentoDoClient, Integer dia, LocalDateTime dataDeCriacao, Proposta proposta) {

        this.idVencimentoDoClient = idVencimentoDoClient;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
        this.proposta = proposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vencimento that = (Vencimento) o;
        return Objects.equals(id, that.id) && Objects.equals(idVencimentoDoClient, that.idVencimentoDoClient) && Objects.equals(dia, that.dia) && Objects.equals(dataDeCriacao, that.dataDeCriacao) && Objects.equals(proposta, that.proposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVencimentoDoClient, dia, dataDeCriacao, proposta);
    }
}
