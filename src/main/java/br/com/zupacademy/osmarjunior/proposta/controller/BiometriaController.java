package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.controller.request.NovaBiometriaRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Biometria;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.repository.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/biometrias")
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarBiometria(@RequestParam("numeroCartao") @NotBlank String numeroCartao,
                                            @Valid NovaBiometriaRequest novaBiometriaRequest,
                                            UriComponentsBuilder uri){
        Optional<Cartao> optionalCartao = cartaoRepository.findByNumeroCartao(numeroCartao);
        if(optionalCartao.isEmpty()){
            logger.error("Cartão informando a função de biometria não existe.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O cartão informado não existe no sistema.");
        }

        Biometria biometria = novaBiometriaRequest.toBiometria(optionalCartao.get());
        entityManager.persist(biometria);

        URI biometriaUrl = uri.path("/api/v1/biometrias/{biometriaId}")
                .buildAndExpand(biometria.getId())
                .toUri();
        logger.info("Nova biometria gerada. Link: " + biometriaUrl.toString());
        return ResponseEntity.created(biometriaUrl).build();
    }
}
