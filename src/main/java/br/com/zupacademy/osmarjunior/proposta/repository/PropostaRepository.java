package br.com.zupacademy.osmarjunior.proposta.repository;

import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, String> {

    Collection<Proposta> findByStatusPropostaAndCartaoIsNull(StatusProposta statusProposta);

    Optional<Proposta> findByEmail(String email);

}
