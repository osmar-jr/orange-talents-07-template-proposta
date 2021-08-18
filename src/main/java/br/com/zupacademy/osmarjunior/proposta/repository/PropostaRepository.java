package br.com.zupacademy.osmarjunior.proposta.repository;

import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, String> {

    List<Proposta> findByCpfOuCnpj(String cpfOuCnpj);

    @Query(value = "SELECT * FROM tb_proposta p WHERE p.status_proposta = 'ELEGIVEL' AND p.numero_cartao IS NULL", nativeQuery = true)
    Collection<Proposta> getPropostasElegiveisSemCartao();
}
