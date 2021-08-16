package br.com.zupacademy.osmarjunior.proposta.annotations;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = {})
@Target({FIELD})
@Retention(RUNTIME)
public @interface CpfOrCnpj {
    String message() default "Value must a CPF or CNPJ.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
