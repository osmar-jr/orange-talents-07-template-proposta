package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.CartoesClient;
import br.com.zupacademy.osmarjunior.proposta.model.*;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;
import br.com.zupacademy.osmarjunior.proposta.repository.PropostaRepository;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnaliseCartao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class NumeroCartaoService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartoesClient cartoesClient;

    private final Logger logger = LoggerFactory.getLogger(NumeroCartaoService.class);

    @Scheduled(fixedDelayString = "${feign.cartoes.fixed-delay}", initialDelayString = "${feign.cartoes.intial-delay}")
    public void tenteObterNumeroCartao(){
        Collection<Proposta> elegiveisSemCartao = propostaRepository.findByStatusPropostaAndCartaoIsNull(StatusProposta.ELEGIVEL);
        if(elegiveisSemCartao.isEmpty()){
            return;
        }

        elegiveisSemCartao.forEach(this::obterCartao);
    }

    private void obterCartao(Proposta proposta) {
        try {
            ResultadoAnaliseCartao resultadoAnaliseCartao = cartoesClient.gerarCartao(proposta.toSolicitacaoAnalise());

            Cartao cartao = resultadoAnaliseCartao.toCartao(proposta);
            proposta.atualizaCartao(cartao);
            propostaRepository.save(proposta);

            logger.info("Novo cartão gerado com sucesso pelo sistema legado.");

        } catch (FeignException.UnprocessableEntity e){
            logger.error("Não foi possível gerar o cartão, entidade não processável.", e);

        } catch (FeignException e){
            logger.error("Erro ao tentar gerar cartão no sistema legado.", e);
        }
    }
}
