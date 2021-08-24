package br.com.zupacademy.osmarjunior.proposta.repository;

import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findByNumeroCartao(String numeroCartao);

}
