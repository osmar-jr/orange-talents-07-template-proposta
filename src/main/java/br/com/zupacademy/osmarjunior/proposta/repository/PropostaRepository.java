package br.com.zupacademy.osmarjunior.proposta.repository;

import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.model.enums.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, String> {

    List<Proposta> findByCpfOuCnpj(String cpfOuCnpj);

    Collection<Proposta> findByStatusPropostaAndCartaoIsNull(StatusProposta statusProposta);
}
