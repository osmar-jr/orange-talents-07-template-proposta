package br.com.zupacademy.osmarjunior.proposta.service.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultadoAvisoViagem {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String resultado;

    @Deprecated
    public ResultadoAvisoViagem() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResultadoAvisoViagem(@JsonFormat(shape = JsonFormat.Shape.STRING) String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
