package br.com.zupacademy.osmarjunior.proposta.handler.dto;

public class SaidaDeErroDeCampoDto {

    private String campo;
    private String erro;

    public SaidaDeErroDeCampoDto() {
    }

    public SaidaDeErroDeCampoDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
