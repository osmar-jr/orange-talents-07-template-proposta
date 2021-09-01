package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.model.Bloqueio;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.repository.BloqueioRepository;
import br.com.zupacademy.osmarjunior.proposta.repository.CartaoRepository;
import br.com.zupacademy.osmarjunior.proposta.service.BloqueioCartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/cartoes/{cartaoId}/bloqueios")
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private BloqueioCartaoService bloqueioCartaoService;

    private final Logger logger = LoggerFactory.getLogger(BloqueioController.class);

    @PostMapping
    public ResponseEntity<?> bloquearCartao(@PathVariable("cartaoId") @NotNull String cartaoId,
                                            HttpServletRequest request,
                                            UriComponentsBuilder uri){
        Optional<Cartao> optionalCartao = cartaoRepository.findByNumeroCartao(cartaoId);
        if(optionalCartao.isEmpty()){
            logger.error("Cartão informando a função de bloqueio não existe.");
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = optionalCartao.get();
        Collection<Bloqueio> bloqueios = bloqueioRepository.findByCartaoAndAtivoTrue(cartao);
        if(!bloqueios.isEmpty()){
            logger.error("O cartão informado já está bloqueado.");
            return ResponseEntity.unprocessableEntity().body("Cartao informado já possui bloqueio ativo.");
        }

        String userAgent = request.getHeader("User-Agent");
        String ip = request.getHeader("X-Forwarded-For");
        bloqueioCartaoService.notificarBloqueio(userAgent, cartao);

        if(!cartao.isBloqueado()){
            logger.error("Solicitação de bloqueio foi concluida no sistema legado, mas houve erro na aplicação local.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao processar bloqueio no servidor interno.");
        }

        Bloqueio bloqueio = new Bloqueio(userAgent, ip, cartao);
        bloqueioRepository.save(bloqueio);

        URI location = uri.path("/api/v1/cartoes/{cartaoId}/bloqueios/{bloqueioId}")
                .buildAndExpand(cartao.getId(), bloqueio.getId())
                .toUri();

        logger.info("O bloqueio foi efetuado com sucesso. Link: " + location.toString());
        return ResponseEntity.ok().location(location).build();
    }
}
