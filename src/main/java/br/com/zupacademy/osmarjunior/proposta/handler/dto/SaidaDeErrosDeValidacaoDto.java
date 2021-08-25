package br.com.zupacademy.osmarjunior.proposta.handler.dto;

import java.util.ArrayList;
import java.util.List;

public class SaidaDeErrosDeValidacaoDto {

    private List<String> errosGlobais = new ArrayList<>();
    private List<SaidaDeErroDeCampoDto> camposDeErro = new ArrayList<>();

    public void adicionarErro(String erro){
        errosGlobais.add(erro);
    }

    public void adicionarCampoDeErro(String campo, String erro){
        SaidaDeErroDeCampoDto erroDeCampoDto = new SaidaDeErroDeCampoDto(campo, erro);
        camposDeErro.add(erroDeCampoDto);
    }

    public List<String> getErrosGlobais() {
        return errosGlobais;
    }

    public List<SaidaDeErroDeCampoDto> getCamposDeErro() {
        return camposDeErro;
    }
}
