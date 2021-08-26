package br.com.zupacademy.osmarjunior.proposta.validators;


import br.com.zupacademy.osmarjunior.proposta.annotations.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    private Class<?> classDomain;
    private String attributeName;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue uniqueValue) {
        this.classDomain = uniqueValue.classDomain();
        this.attributeName = uniqueValue.attributeName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from " + classDomain.getName() + " where " + attributeName + "=:value");
        query.setParameter("value", value);

        List<?> resultList = query.getResultList();

        Assert.state(resultList.size() <= 1, "Ja existem registros com o valor: " + value);

        return resultList.isEmpty();
    }
}
