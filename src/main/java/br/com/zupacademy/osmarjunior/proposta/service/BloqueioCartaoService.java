package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.CartoesClient;
import br.com.zupacademy.osmarjunior.proposta.model.Bloqueio;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusCartao;
import br.com.zupacademy.osmarjunior.proposta.repository.BloqueioRepository;
import br.com.zupacademy.osmarjunior.proposta.repository.CartaoRepository;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoBloqueio;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoBloqueio;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class BloqueioCartaoService {

    @Autowired
    private CartoesClient cartoesClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    public void notificarBloqueio(String sistemaResponsavel, Cartao cartao) {
        try {

            SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio(sistemaResponsavel);
            ResultadoBloqueio resultadoBloqueio = cartoesClient.solicitaBloqueio(cartao.getId(), solicitacaoBloqueio);

            StatusCartao statusCartao = resultadoBloqueio.toStatusCartao();

            cartao.atualizaStatusCartao(statusCartao);
            cartaoRepository.save(cartao);

        } catch (FeignException exception){

            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                   "Sistema da Operadora do Cartão Indisponível." +
                           " STATUS FEIGN: " + exception.status() +
                           "\nERROR: " + exception.getMessage());
        }
    }
}
