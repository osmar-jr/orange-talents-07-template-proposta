package br.com.zupacademy.osmarjunior.proposta.clients;

import br.com.zupacademy.osmarjunior.proposta.controller.request.AvisoRequest;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoAnalise;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoBloqueio;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoCarteira;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAvisoViagem;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnaliseCartao;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoBloqueio;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoCarteiraDigital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "${feign.cartoes.name}", url = "${feign.cartoes.url}")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes")
    ResultadoAnaliseCartao gerarCartao(@RequestBody @Valid SolicitacaoAnalise solicitacaoAnalise);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/bloqueios")
    ResultadoBloqueio gerarBloqueio(@PathVariable String id, @Valid @RequestBody SolicitacaoBloqueio solicitacaoBloqueio);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/avisos")
    ResultadoAvisoViagem notificarViagem(@PathVariable String id, @Valid @RequestBody AvisoRequest avisoRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/carteiras")
    ResultadoCarteiraDigital gerarCarteiraDigital(@PathVariable String id, @Valid @RequestBody SolicitacaoCarteira solicitacaoCarteira);

}
