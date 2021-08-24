package br.com.zupacademy.osmarjunior.proposta.controller.request;

import br.com.zupacademy.osmarjunior.proposta.model.Aviso;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate validoAte;
    @NotBlank
    private String destino;

    public AvisoRequest(@NotNull LocalDate validoAte,
                        @NotBlank String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso toAviso(String userAgent, String ip, Cartao cartao) {
        return new Aviso(this.validoAte, this.destino, userAgent, ip, cartao);
    }
}
