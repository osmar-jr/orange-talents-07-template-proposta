package br.com.zupacademy.osmarjunior.proposta.controller.response;

import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PropostaResponse {

    private String id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    public PropostaResponse(String id, String nome, StatusProposta statusProposta) {
        this.id = id;
        this.nome = nome;
        this.statusProposta = statusProposta;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
