package br.com.zupacademy.osmarjunior.proposta.annotations;

import br.com.zupacademy.osmarjunior.proposta.validators.IsBase64Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = IsBase64Validator.class)
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface IsBase64 {

    String message() default "Value must be a Base64.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
