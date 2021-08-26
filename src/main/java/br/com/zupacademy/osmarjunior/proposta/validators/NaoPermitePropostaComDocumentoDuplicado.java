package br.com.zupacademy.osmarjunior.proposta.validators;

import br.com.zupacademy.osmarjunior.proposta.controller.request.NovaPropostaRequest;
import br.com.zupacademy.osmarjunior.proposta.model.utils.DocumentoLimpo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class NaoPermitePropostaComDocumentoDuplicado implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaPropostaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NovaPropostaRequest request = (NovaPropostaRequest) target;
        String cpfOuCnpj = request.getCpfOuCnpj();

        DocumentoLimpo documentoLimpo = new DocumentoLimpo(request.getCpfOuCnpj());
        String documentoHash = documentoLimpo.hash();

        Query query = entityManager.createQuery("select p from Proposta p where p.cpfOuCnpj=:hash");
        query.setParameter("hash", documentoHash);

        List<?> resultList = query.getResultList();

        if (!resultList.isEmpty()) {
            errors.rejectValue("cpfOuCnpj", HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                    "Já existe uma proposta com o número de documento: " + cpfOuCnpj);
        }

    }
}
