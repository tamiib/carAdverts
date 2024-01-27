package com.codevibe.codevibe.validators;

import com.codevibe.codevibe.dto.CarAdvertDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CarAdvertValidator implements ConstraintValidator<CarAdvertConstraint ,CarAdvertDto> {

    @Override
    public boolean isValid(CarAdvertDto dto, ConstraintValidatorContext context) 
    {
        boolean valid = true;
        if (Boolean.TRUE.equals(dto.getIsNew())) 
        {
            if (dto.getMileage() != null && dto.getMileage() != 0) 
            {
                context.buildConstraintViolationWithTemplate("New car must have mileage 0").addPropertyNode("mileage").addConstraintViolation();
                valid = false;
            }

            if (dto.getFirstRegistration() != null) 
            {
                context.buildConstraintViolationWithTemplate("New car cannot have a first registration date").addPropertyNode("firstRegistration").addConstraintViolation();
                valid = false;
            }

            if (!valid) 
            {
                context.disableDefaultConstraintViolation();
            }
        }

        return valid;
    }
}
