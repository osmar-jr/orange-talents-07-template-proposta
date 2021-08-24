package br.com.zupacademy.osmarjunior.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate validoAte;
    private String destino;

    private String userAgent;
    private String ip;
    private LocalDateTime criadoEm = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDate validoAte, String destino, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
    }

    public Aviso(@NotNull @Future LocalDate validoAte,
                 @NotBlank String destino,
                 String userAgent,
                 String ip,
                 @NotNull Cartao cartao) {

        this.validoAte = validoAte;
        this.destino = destino;
        this.userAgent = userAgent;
        this.ip = ip;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
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
