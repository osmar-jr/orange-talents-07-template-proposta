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

    private String idCartoesClient;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private Boolean ativo;

    @ManyToOne
    private Proposta proposta;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String idCartoesClient, LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo, Proposta proposta) {

        this.idCartoesClient = idCartoesClient;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.proposta = proposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return Objects.equals(id, bloqueio.id) && Objects.equals(idCartoesClient, bloqueio.idCartoesClient) && Objects.equals(bloqueadoEm, bloqueio.bloqueadoEm) && Objects.equals(sistemaResponsavel, bloqueio.sistemaResponsavel) && Objects.equals(ativo, bloqueio.ativo) && Objects.equals(proposta, bloqueio.proposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCartoesClient, bloqueadoEm, sistemaResponsavel, ativo, proposta);
    }
}
