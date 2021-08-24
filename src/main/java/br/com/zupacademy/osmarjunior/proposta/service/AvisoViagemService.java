package br.com.zupacademy.osmarjunior.proposta.service;

import br.com.zupacademy.osmarjunior.proposta.clients.CartoesClient;
import br.com.zupacademy.osmarjunior.proposta.controller.request.AvisoRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.service.response.ResultadoAvisoViagem;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class AvisoViagemService {

    @Autowired
    private CartoesClient cartoesClient;

    public void processaAviso(@NotNull @Valid AvisoRequest avisoRequest,
                              @NotNull Cartao cartao) {
        try{
            ResultadoAvisoViagem resultadoAvisoViagem = cartoesClient.notificarViagem(cartao.getNumeroCartao(), avisoRequest);
            Assert.notNull(resultadoAvisoViagem, "Erro ao processar aviso, tente novamente mais tarde.");
            Assert.state(resultadoAvisoViagem.getResultado().equalsIgnoreCase("CRIADO"),
                    "Aviso não pode ser criado pelo sistema bancário, tente novamemente mais tarde.");

        } catch (FeignException exception){
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Sistema bancário indisponível." +
                            " FEIGN CODE: " + exception.status()
                            + "\n" + exception.getMessage());
        }
    }
}
