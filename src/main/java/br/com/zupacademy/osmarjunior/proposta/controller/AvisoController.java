package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.controller.request.AvisoRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Aviso;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.repository.AvisoRepository;
import br.com.zupacademy.osmarjunior.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cartoes/{cartaoId}/avisos")
public class AvisoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoRepository avisoRepository;

    @PostMapping
    public ResponseEntity<?> adicionarAviso(@PathVariable("cartaoId") String cartaoId,
                                            HttpServletRequest request,
                                            @Valid AvisoRequest avisoRequest,
                                            UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> optionalCartao = cartaoRepository.findByNumeroCartao(cartaoId);

        if(optionalCartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O cartão informado não existe.");
        }

        String userAgent = request.getHeader("User-Agent");
        String ip = request.getHeader("X-Forwarded-For");
        Cartao cartao = optionalCartao.get();

        Aviso aviso = avisoRequest.toAviso(userAgent, ip, cartao);
        avisoRepository.save(aviso);

        URI uriAviso = uriComponentsBuilder.path("/api/v1/cartoes/{cartaoId}/avisos/{avisoId}")
                .buildAndExpand(cartao.getId(), aviso.getId())
                .toUri();

        return ResponseEntity.ok().location(uriAviso).build();
    }
}
