package br.com.zupacademy.osmarjunior.proposta.service.response;

import br.com.zupacademy.osmarjunior.proposta.controller.request.CarteiraDigitalRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.model.Carteira;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ResultadoCarteiraDigital {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String resultado;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResultadoCarteiraDigital(@JsonFormat(shape = JsonFormat.Shape.STRING) String resultado,
                                    @JsonFormat(shape = JsonFormat.Shape.STRING) String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public boolean isAssociada() {
        return this.resultado.equalsIgnoreCase("ASSOCIADA");
    }


    public Carteira toCarteira(@NotNull Cartao cartao,
                               @NotNull @Valid CarteiraDigitalRequest carteiraDigitalRequest) {
        String nomeEmissor = carteiraDigitalRequest.getValorEmissor();
        String email = carteiraDigitalRequest.getEmail();
        return new Carteira(this.id, email, LocalDateTime.now(), nomeEmissor, cartao);
    }
}
