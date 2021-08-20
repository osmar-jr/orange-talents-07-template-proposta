package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.AnalisePropostaClient;
import br.com.zupacademy.osmarjunior.proposta.model.enums.ResultadoSolicitacao;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnalise;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Override
    public ResultadoSolicitacao executa(@NotNull @Valid SolicitacaoAnalise solicitacaoAnalise) {

        try{
            ResultadoAnalise resultadoAnalise = analisePropostaClient.analisaProposta(solicitacaoAnalise);

            return resultadoAnalise.getResultadoSolicitacao();

        } catch (FeignException.UnprocessableEntity e){
            System.out.println("\nHTTP 422\nERROR: " + e.getMessage());
            return ResultadoSolicitacao.COM_RESTRICAO;

        } catch (FeignException e){
            System.out.println("\nDEU RUIM FEIGN EXCEPTION\nERROR: " + e.getMessage());
            e.printStackTrace();

            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Serviço de análise financeira indisponível. Tente novamente mais tarde.");
        }
    }
}
