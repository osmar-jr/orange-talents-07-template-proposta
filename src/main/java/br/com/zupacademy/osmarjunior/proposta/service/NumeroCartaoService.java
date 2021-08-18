package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.CartoesClient;
import br.com.zupacademy.osmarjunior.proposta.model.*;
import br.com.zupacademy.osmarjunior.proposta.repository.PropostaRepository;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAnaliseCartao;
import br.com.zupacademy.osmarjunior.proposta.service.response.dto.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Component
public class NumeroCartaoService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartoesClient cartoesClient;

    @Scheduled(fixedDelayString = "${feign.cartoes.fixed-delay}", initialDelayString = "${feign.cartoes.intial-delay}")
    public void tenteObterNumeroCartao(){
        Collection<Proposta> elegiveisSemCartao = propostaRepository.getPropostasElegiveisSemCartao();
        if(elegiveisSemCartao.isEmpty()){
            System.out.println("\n\n\nNADA PRA ATUALIZAR\n\n\n");
            return;
        }

        elegiveisSemCartao.forEach(this::obterCartao);
    }

    private void obterCartao(Proposta proposta) {
        try {
            ResultadoAnaliseCartao resultadoAnaliseCartao = cartoesClient.gerarCartao(proposta.toSolicitacaoAnalise());

            Set<Bloqueio> bloqueios = BloqueioDto.toBloqueiosList(resultadoAnaliseCartao.getBloqueios(), proposta);
            Set<Aviso> avisos = AvisoDto.toAvisosList(resultadoAnaliseCartao.getAvisos(), proposta);
            Set<Carteira> carteiras = CarteiraDto.toCarteirasList(resultadoAnaliseCartao.getCarteiras(), proposta);
            Set<Parcela> parcelas = ParcelaDto.toParcelasList(resultadoAnaliseCartao.getParcelas(), proposta);
            String cartaoId = resultadoAnaliseCartao.getId();
            LocalDateTime cartaoEmitidoEm = resultadoAnaliseCartao.getEmitidoEm();
            BigDecimal limite = resultadoAnaliseCartao.getLimite();

            proposta.atualizaVencimento(resultadoAnaliseCartao.getVencimento());
            proposta.atualizaRenegociacao(resultadoAnaliseCartao.getRenegociacao());

            proposta.atualizaDadosDeCartao(cartaoId, cartaoEmitidoEm, limite, bloqueios, avisos, carteiras, parcelas);

            propostaRepository.save(proposta);

            System.out.println("\n\n\nSALVOU ATUALIZAÇÕES\n\n\n");

        } catch (FeignException.UnprocessableEntity e){
            System.out.println("\n\n\nDEU RUIM HTTP 422\n\n\n");
            e.printStackTrace();

        } catch (FeignException e){

            System.out.println("\n\n\nDEU RUIM FEIGN EXCEPTION\n\n\n");
            e.printStackTrace();
        }
    }
}
