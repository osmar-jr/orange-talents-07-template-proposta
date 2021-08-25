package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.CartoesClient;
import br.com.zupacademy.osmarjunior.proposta.controller.request.CarteiraDigitalRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.model.Carteira;
import br.com.zupacademy.osmarjunior.proposta.service.request.SolicitacaoCarteira;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoCarteiraDigital;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class SolicitaCarteiraService {

    @Autowired
    private CartoesClient cartoesClient;

    public Carteira processarSolicitacao(@NotNull Cartao cartao,
                                         @NotNull @Valid CarteiraDigitalRequest carteiraDigitalRequest) {
        try {
            SolicitacaoCarteira solicitacaoCarteira = carteiraDigitalRequest.toSolicitacaoCarteira();
            ResultadoCarteiraDigital resultadoCarteiraDigital = cartoesClient.gerarCarteiraDigital(cartao.getNumeroCartao(), solicitacaoCarteira);
            Assert.isTrue(resultadoCarteiraDigital.isAssociada(),
                    "Não foi possível criar a carteira digital.");

            return resultadoCarteiraDigital.toCarteira(cartao, carteiraDigitalRequest);

        } catch (FeignException feignException){
            feignException.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Não foi possível processar a solicitação de Carteira." +
                            " Sistema da Operadora do Cartão Indisponível." +
                            " STATUS FEIGN: " + feignException.status() +
                            "\nERROR: " + feignException.getMessage());
        }
    }
}
