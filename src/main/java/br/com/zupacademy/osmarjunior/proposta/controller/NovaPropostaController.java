package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.controller.request.NovaPropostaRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.model.enums.ResultadoSolicitacao;
import br.com.zupacademy.osmarjunior.proposta.service.Analisa;
import br.com.zupacademy.osmarjunior.proposta.validators.NaoPermitePropostaComDocumentoDuplicado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/propostas")
public class NovaPropostaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NaoPermitePropostaComDocumentoDuplicado naoPermitePropostaComDocumentoDuplicado;

    @Autowired
    private Analisa analiseCartaoService;

    private final Logger logger = LoggerFactory.getLogger(NovaPropostaController.class);

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(naoPermitePropostaComDocumentoDuplicado);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                          UriComponentsBuilder uri){
        Proposta proposta = novaPropostaRequest.toProposta();
        entityManager.persist(proposta);

        ResultadoSolicitacao resultadoSolicitacao = analiseCartaoService.executa(proposta.toSolicitacaoAnalise());
        proposta.setStatusProposta(resultadoSolicitacao.toStatusProposta());

        URI createdResourceLink = uri
                .path("/api/v1/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
        logger.info("Nova Proposta Criada com Sucesso. Link: " + createdResourceLink.toString());
        return ResponseEntity.created(createdResourceLink).build();
    }
}
