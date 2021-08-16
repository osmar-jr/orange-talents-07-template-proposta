package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.controller.requests.NovaPropostaRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                          UriComponentsBuilder uri){
        Proposta proposta = novaPropostaRequest.toProposta();
        entityManager.persist(proposta);
        URI createdResourceLink = uri
                .path("/api/v1/propostas/{serialId}")
                .buildAndExpand(proposta.getSerialId())
                .toUri();
        return ResponseEntity.created(createdResourceLink).build();
    }
}
