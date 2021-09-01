package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.CartoesClient;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusCartao;
import br.com.zupacademy.osmarjunior.proposta.repository.CartaoRepository;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoBloqueio;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoBloqueio;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
public class BloqueioCartaoService {

    @Autowired
    private CartoesClient cartoesClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    private final Logger logger = LoggerFactory.getLogger(BloqueioCartaoService.class);

    public void notificarBloqueio(@NotBlank String sistemaResponsavel, @NotNull Cartao cartao) {
        try {

            SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio(sistemaResponsavel);
            ResultadoBloqueio resultadoBloqueio = cartoesClient.gerarBloqueio(cartao.getNumeroCartao(), solicitacaoBloqueio);

            StatusCartao statusCartao = resultadoBloqueio.toStatusCartao();

            cartao.atualizaStatusCartao(statusCartao);
            cartaoRepository.save(cartao);
            logger.info("Nova notificação de Bloqueio salvo.");
        } catch (FeignException exception){
            logger.error("Houve ao notificar o Bloqueio do cartão ao sistema legado.", exception);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                   "Sistema da Operadora do Cartão Indisponível." +
                           " STATUS FEIGN: " + exception.status() +
                           "\nERROR: " + exception.getMessage());
        }
    }
}
