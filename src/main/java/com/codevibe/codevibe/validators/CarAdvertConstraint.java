package com.codevibe.codevibe.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CarAdvertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CarAdvertConstraint 
{
    String message() default "Validation failed! Invalid car advert!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    
}
