package com.InternationalPassport.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomerAgeConstrainValidator.class)
public @interface CustomerAgeConstrain {
    String message() default "{negative.value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
