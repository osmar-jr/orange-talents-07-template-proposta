package br.com.zupacademy.osmarjunior.proposta.validators;

import br.com.zupacademy.osmarjunior.proposta.annotations.IsBase64;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try{
            Assert.notNull(value, "Value must not be null.");

            byte[] decoded = Base64.getDecoder().decode(String.valueOf(value));

            Assert.notNull(decoded, "Value cannot be decoded.");
            return true;
        } catch (Exception exception){

            System.out.println("VALUE IS NOT A BASE64 ENCODED.\nERROR: " + exception.getMessage());
            return false;
        }
    }
}
