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
    private Proposta proposta;

    @Deprecated
    public Aviso() {
    }

    public Aviso(String validoAte, String destino, Proposta proposta) {

        this.validoAte = validoAte;
        this.destino = destino;
        this.proposta = proposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aviso aviso = (Aviso) o;
        return Objects.equals(id, aviso.id) && Objects.equals(validoAte, aviso.validoAte) && Objects.equals(destino, aviso.destino) && Objects.equals(proposta, aviso.proposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validoAte, destino, proposta);
    }
}
