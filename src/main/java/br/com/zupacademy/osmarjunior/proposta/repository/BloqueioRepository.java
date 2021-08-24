package br.com.zupacademy.osmarjunior.proposta.repository;

import br.com.zupacademy.osmarjunior.proposta.model.Bloqueio;
import br.com.zupacademy.osmarjunior.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {

    Collection<Bloqueio> findByCartaoAndAtivoTrue(Cartao cartao);
}
