package br.com.zupacademy.osmarjunior.proposta.controller.request;

import br.com.zupacademy.osmarjunior.proposta.model.Aviso;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String destino;

    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    @Deprecated
    public AvisoRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoRequest(@NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING) String destino,
                        @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public Aviso toAviso(String userAgent, String ip, Cartao cartao) {
        return new Aviso(this.validoAte, this.destino, userAgent, ip, cartao);
    }
}
