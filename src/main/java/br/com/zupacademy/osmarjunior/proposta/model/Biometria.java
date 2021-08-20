package br.com.zupacademy.osmarjunior.proposta.model;

import br.com.zupacademy.osmarjunior.proposta.annotations.IsBase64;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @IsBase64
    private String biometria;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(@NotBlank String biometria, @NotNull Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }

    /**
     * Getter Id for link resource creation
     */
    public Long getId() {
        return id;
    }
}
