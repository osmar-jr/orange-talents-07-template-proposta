package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.model.enums.ResultadoSolicitacao;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public interface Analisa {

    ResultadoSolicitacao executa(@NotNull @Valid SolicitacaoAnalise solicitacaoAnalise);
}
