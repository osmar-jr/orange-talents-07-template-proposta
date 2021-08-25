package br.com.zupacademy.osmarjunior.proposta.repository;

import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import br.com.zupacademy.osmarjunior.proposta.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Collection<Carteira> findByCartaoAndEmissor(Cartao cartao, String emissor);
}
