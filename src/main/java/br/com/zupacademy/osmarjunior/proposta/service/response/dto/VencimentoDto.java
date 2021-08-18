package br.com.zupacademy.osmarjunior.proposta.service.response.dto;

import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.model.Vencimento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class VencimentoDto {

    private String id;
    private Integer dia;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataDeCriacao;

    public VencimentoDto(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    @Override
    public String toString() {
        return "VencimentoDto{" +
                "id='" + id + '\'' +
                ", dia=" + dia +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }

    public Vencimento toVencimento(Proposta proposta) {
        return new Vencimento(this.id, this.dia, this.dataDeCriacao, proposta);
    }
}
