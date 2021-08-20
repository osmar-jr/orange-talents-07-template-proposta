package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.controller.request.NovaPropostaRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.model.enums.ResultadoSolicitacao;
import br.com.zupacademy.osmarjunior.proposta.service.AnaliseCartaoService;
import br.com.zupacademy.osmarjunior.proposta.validators.NaoPermitePropostaComDocumentoDuplicado;
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
    private AnaliseCartaoService analiseCartaoService;

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
        return ResponseEntity.created(createdResourceLink).build();
    }
}
