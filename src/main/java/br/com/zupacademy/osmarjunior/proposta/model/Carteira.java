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

    private String idCarteiraCartoesClient;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String idCarteiraCartoesClient, String email, LocalDateTime associadaEm, String emissor, Cartao cartao) {
        this.idCarteiraCartoesClient = idCarteiraCartoesClient;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return Objects.equals(id, carteira.id) && Objects.equals(idCarteiraCartoesClient, carteira.idCarteiraCartoesClient) && Objects.equals(email, carteira.email) && Objects.equals(associadaEm, carteira.associadaEm) && Objects.equals(emissor, carteira.emissor) && Objects.equals(cartao, carteira.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCarteiraCartoesClient, email, associadaEm, emissor, cartao);
    }

    public Long getId() {
        return id;
    }
}
