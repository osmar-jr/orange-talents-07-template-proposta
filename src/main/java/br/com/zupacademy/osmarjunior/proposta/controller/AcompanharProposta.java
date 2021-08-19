package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.controller.response.PropostaResponse;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/acompanhar-proposta")
public class AcompanharProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping
    public ResponseEntity<PropostaResponse> acompanheProposta(@RequestParam(name = "propostaId") String propostaId){
        Optional<Proposta> optionalProposta = propostaRepository.findById(propostaId);

        if(optionalProposta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Proposta informada não foi encontrada.");
        }

        Proposta proposta = optionalProposta.get();
        return ResponseEntity.ok().body(proposta.toResponse());
    }
}
