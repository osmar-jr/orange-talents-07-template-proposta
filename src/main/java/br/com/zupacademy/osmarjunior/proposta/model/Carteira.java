package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String idCartoesClient;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @ManyToOne
    private Proposta proposta;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String idCartoesClient, String email, LocalDateTime associadaEm, String emissor, Proposta proposta) {

        this.idCartoesClient = idCartoesClient;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
        this.proposta = proposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return Objects.equals(id, carteira.id) && Objects.equals(idCartoesClient, carteira.idCartoesClient) && Objects.equals(email, carteira.email) && Objects.equals(associadaEm, carteira.associadaEm) && Objects.equals(emissor, carteira.emissor) && Objects.equals(proposta, carteira.proposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCartoesClient, email, associadaEm, emissor, proposta);
    }
}
