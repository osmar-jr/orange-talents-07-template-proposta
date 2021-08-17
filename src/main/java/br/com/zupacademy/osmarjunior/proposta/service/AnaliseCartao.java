package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.model.enums.ResultadoSolicitacao;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnalise;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Service
@Primary
public class AnaliseCartao implements Analisa {

    @Override
    public ResultadoSolicitacao executa(@NotNull @Valid SolicitacaoAnalise solicitacaoAnalise) {
        Map<String, String> request = Map.of(
                "documento", solicitacaoAnalise.getDocumento(),
                "nome", solicitacaoAnalise.getNome(),
                "idProposta", solicitacaoAnalise.getIdProposta()
        );

        RestTemplate restTemplate = new RestTemplate();

        try{
            ResponseEntity<ResultadoAnalise> response = restTemplate.postForEntity("http://localhost:9999/api/solicitacao",
                    request,
                    ResultadoAnalise.class);

            ResultadoAnalise resultadoAnalise = response.getBody();
            Assert.notNull(resultadoAnalise, "Erro ao processar o resultado da análise.");

            return resultadoAnalise.getResultadoSolicitacao();

        } catch (Exception e){

            return ResultadoSolicitacao.COM_RESTRICAO;
        }
    }
}
