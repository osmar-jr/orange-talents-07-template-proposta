package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String validoAte;
    private String destino;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(String validoAte, String destino, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aviso aviso = (Aviso) o;
        return Objects.equals(id, aviso.id) && Objects.equals(validoAte, aviso.validoAte) && Objects.equals(destino, aviso.destino) && Objects.equals(cartao, aviso.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validoAte, destino, cartao);
    }
}
