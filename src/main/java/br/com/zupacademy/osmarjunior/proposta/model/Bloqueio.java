package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String idBloqueioCartoesClient;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private Boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String idBloqueioCartoesClient, LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo, Cartao cartao) {
        this.idBloqueioCartoesClient = idBloqueioCartoesClient;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return Objects.equals(id, bloqueio.id) && Objects.equals(idBloqueioCartoesClient, bloqueio.idBloqueioCartoesClient) && Objects.equals(bloqueadoEm, bloqueio.bloqueadoEm) && Objects.equals(sistemaResponsavel, bloqueio.sistemaResponsavel) && Objects.equals(ativo, bloqueio.ativo) && Objects.equals(cartao, bloqueio.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBloqueioCartoesClient, bloqueadoEm, sistemaResponsavel, ativo, cartao);
    }
}
