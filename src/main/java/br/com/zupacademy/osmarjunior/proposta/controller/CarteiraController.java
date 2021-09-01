package br.com.zupacademy.osmarjunior.proposta.controller;

import br.com.zupacademy.osmarjunior.proposta.controller.request.CarteiraDigitalRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.model.Carteira;
import br.com.zupacademy.osmarjunior.proposta.repository.CartaoRepository;
import br.com.zupacademy.osmarjunior.proposta.repository.CarteiraRepository;
import br.com.zupacademy.osmarjunior.proposta.service.SolicitaCarteiraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cartoes/{cartaoId}/carteiras")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private SolicitaCarteiraService solicitaCarteiraService;

    private final Logger logger = LoggerFactory.getLogger(CarteiraController.class);

    @PostMapping
    public ResponseEntity<?> solicitaCarteira(@PathVariable("cartaoId") String cartaoId,
                                              @RequestBody @Valid CarteiraDigitalRequest carteiraDigitalRequest,
                                              UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> optionalCartao = cartaoRepository.findByNumeroCartao(cartaoId);
        if(optionalCartao.isEmpty()){
            logger.error("Cartão informado na solicitação de carteira não existe.");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("O cartão informado não foi encontrado no sistema.");
        }

        Cartao cartao = optionalCartao.get();
        String nomeEmissor = carteiraDigitalRequest.getValorEmissor();
        Collection<Carteira> carteiras = carteiraRepository.findByCartaoAndEmissor(cartao, nomeEmissor);
        if(!carteiras.isEmpty()){
            logger.error("A carteira solicitada já está associada ao cartão informado.");
            return ResponseEntity
                    .unprocessableEntity()
                    .body("O cartao informado já está associado ao serviço " + nomeEmissor + ".");
        }

        Carteira carteira = solicitaCarteiraService.processarSolicitacao(cartao, carteiraDigitalRequest);
        carteiraRepository.save(carteira);

        URI carteiraLocation = uriComponentsBuilder
                .path("/api/v1/cartoes/{cartaoId}/carteiras/{carteiraId}")
                .buildAndExpand(cartao.getId(), carteira.getId())
                .toUri();

        logger.info("Carteira inserida com sucesso. Link: " + carteiraLocation.toString());
        return ResponseEntity
                .created(carteiraLocation)
                .build();
    }
}
