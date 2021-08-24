package br.com.zupacademy.osmarjunior.proposta.service.response;

import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusCartao;
import com.fasterxml.jackson.annotation.JsonCreator;

public class ResultadoBloqueio {

    private String resultado;

    @Deprecated
    public ResultadoBloqueio() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResultadoBloqueio(String resultado) {
        this.resultado = resultado;
    }

    /**
     * Setter para de/seriazalizacao no sistema legado
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public StatusCartao toStatusCartao() {
        if(resultado.equalsIgnoreCase("BLOQUEADO")){
            return StatusCartao.BLOQUEADO;
        }
        return StatusCartao.ATIVO;
    }
}
