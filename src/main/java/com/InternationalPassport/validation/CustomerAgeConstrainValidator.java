package com.InternationalPassport.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerAgeConstrainValidator implements ConstraintValidator<CustomerAgeConstrain, Integer> {

    public CustomerAgeConstrainValidator(){ }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        if(age == null) {
            return false;
        }
        return age > 0;
    }
}
