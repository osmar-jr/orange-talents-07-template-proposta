package br.com.zupacademy.osmarjunior.proposta.clients;

import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnaliseCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "${feign.cartoes.name}", url = "${feign.cartoes.url}")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes")
    ResultadoAnaliseCartao gerarCartao(@RequestBody @Valid SolicitacaoAnalise solicitacaoAnalise);
}
