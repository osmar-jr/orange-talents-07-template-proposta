package br.com.zupacademy.osmarjunior.proposta.handler;

import br.com.zupacademy.osmarjunior.proposta.handler.dto.SaidaDeErrosDeValidacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SaidaDeErrosDeValidacaoDto handleValidationError(MethodArgumentNotValidException exception) {
        List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return getSaidaDeErrosDeValidacaoDto(objectErrors, fieldErrors);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public SaidaDeErrosDeValidacaoDto handleValidationError(BindException exception) {

        List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return getSaidaDeErrosDeValidacaoDto(objectErrors, fieldErrors);
    }

    private SaidaDeErrosDeValidacaoDto getSaidaDeErrosDeValidacaoDto(List<ObjectError> objectErrors,
                                                                     List<FieldError> fieldErrors){
        SaidaDeErrosDeValidacaoDto errosDeValidacaoDto = new SaidaDeErrosDeValidacaoDto();

        objectErrors.forEach(objectError -> errosDeValidacaoDto.adicionarErro(
                getMensagemDeErro(objectError)
        ));

        fieldErrors.forEach(fieldError -> {
            String mensagemDeErro = getMensagemDeErro(fieldError);
            errosDeValidacaoDto.adicionarCampoDeErro(fieldError.getField(), mensagemDeErro);
        });

        return errosDeValidacaoDto;

    }

    private String getMensagemDeErro(ObjectError objectError){
        return messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
    }
}
