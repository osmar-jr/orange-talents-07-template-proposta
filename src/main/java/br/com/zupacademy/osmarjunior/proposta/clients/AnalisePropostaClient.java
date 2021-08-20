package br.com.zupacademy.osmarjunior.proposta.clients;

import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnalise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "${feign.analise.name}", url = "${feign.analise.url}")
public interface AnalisePropostaClient {

    @RequestMapping(method = RequestMethod.POST, value="/api/solicitacao")
    ResultadoAnalise analisaProposta(@RequestBody @Valid SolicitacaoAnalise solicitacaoAnalise);
}
