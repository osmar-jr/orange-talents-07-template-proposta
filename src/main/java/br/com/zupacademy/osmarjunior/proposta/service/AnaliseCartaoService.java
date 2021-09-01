package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.AnalisePropostaClient;
import br.com.zupacademy.osmarjunior.proposta.model.enums.ResultadoSolicitacao;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnalise;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Primary
public class AnaliseCartaoService implements Analisa {

    @Autowired
    private AnalisePropostaClient analisePropostaClient;

    private final Logger logger = LoggerFactory.getLogger(AnaliseCartaoService.class);

    @Override
    public ResultadoSolicitacao executa(@NotNull @Valid SolicitacaoAnalise solicitacaoAnalise) {

        try{
            ResultadoAnalise resultadoAnalise = analisePropostaClient.analisaProposta(solicitacaoAnalise);
            logger.info("Nova análise SEM_RESTRIÇÃO realizada.");
            return resultadoAnalise.getResultadoSolicitacao();

        } catch (FeignException.UnprocessableEntity e){
            logger.error("Nova análise COM_RESTRIÇÃO realizada.", e);
            return ResultadoSolicitacao.COM_RESTRICAO;

        } catch (FeignException e){
            logger.error("Análise não realizada, servidor Gateway indisponível.", e);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Serviço de análise financeira indisponível. Tente novamente mais tarde.");
        }
    }
}
