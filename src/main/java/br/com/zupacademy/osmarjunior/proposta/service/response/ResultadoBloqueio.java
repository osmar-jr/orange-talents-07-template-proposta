package br.com.zupacademy.osmarjunior.proposta.service.response;

import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusCartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultadoBloqueio {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String resultado;

    @Deprecated
    public ResultadoBloqueio() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResultadoBloqueio(@JsonFormat(shape = JsonFormat.Shape.STRING) String resultado) {
        this.resultado = resultado;
    }

    public StatusCartao toStatusCartao() {
        if(resultado.equalsIgnoreCase("BLOQUEADO")){
            return StatusCartao.BLOQUEADO;
        }
        return StatusCartao.ATIVO;
    }
}
