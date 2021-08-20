package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.CartoesClient;
import br.com.zupacademy.osmarjunior.proposta.model.*;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;
import br.com.zupacademy.osmarjunior.proposta.repository.PropostaRepository;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnaliseCartao;
import feign.FeignException;
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

    @Scheduled(fixedDelayString = "${feign.cartoes.fixed-delay}", initialDelayString = "${feign.cartoes.intial-delay}")
    public void tenteObterNumeroCartao(){
        Collection<Proposta> elegiveisSemCartao = propostaRepository.findByStatusPropostaAndCartaoIsNull(StatusProposta.ELEGIVEL);
        if(elegiveisSemCartao.isEmpty()){
            System.out.println("\nNADA PRA ATUALIZAR\n");
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

            System.out.println("\nSALVOU ATUALIZAÇÕES\n");

        } catch (FeignException.UnprocessableEntity e){
            System.out.println("\nDEU RUIM HTTP 422\nERROR: " + e.getMessage());
            e.printStackTrace();

        } catch (FeignException e){

            System.out.println("\nDEU RUIM FEIGN EXCEPTION\nERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
