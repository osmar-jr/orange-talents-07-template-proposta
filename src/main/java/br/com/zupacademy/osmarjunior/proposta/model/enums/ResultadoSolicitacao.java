package br.com.zupacademy.osmarjunior.proposta.model.enums;

public enum ResultadoSolicitacao {
    COM_RESTRICAO,
    SEM_RESTRICAO;

    public StatusProposta toStatusProposta() {
        if(this.equals(ResultadoSolicitacao.COM_RESTRICAO)){
            return StatusProposta.NAO_ELEGIVEL;
        }
        return StatusProposta.ELEGIVEL;
    }
}
