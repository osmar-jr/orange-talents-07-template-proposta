package br.com.zupacademy.osmarjunior.proposta.validators;

import br.com.zupacademy.osmarjunior.proposta.controller.request.NovaPropostaRequest;
import br.com.zupacademy.osmarjunior.proposta.model.Proposta;
import br.com.zupacademy.osmarjunior.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class NaoPermitePropostaComDocumentoDuplicado implements Validator {

    @Autowired
    private PropostaRepository propostaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaPropostaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()){
            return;
        }

        NovaPropostaRequest request = (NovaPropostaRequest) target;
        List<Proposta> propostasEncontradas = propostaRepository.findByCpfOuCnpj(request.getCpfOuCnpj());

        if(!propostasEncontradas.isEmpty()){
            errors.rejectValue("cpfOuCnpj", HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                    "Já existe uma proposta com o número de documento: " + request.getCpfOuCnpj());
        }

    }
}
