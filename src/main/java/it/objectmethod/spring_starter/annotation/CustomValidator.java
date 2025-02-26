package it.objectmethod.spring_starter.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<CustomValidation, String> {
    @Override
    public void initialize(CustomValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //basically same validation logic of @NotEmpty but worse
        Boolean valid = Boolean.FALSE;
        if(!value.equals("")) {
            valid = Boolean.TRUE;
        }

        //return true if validation passes, false otherwise
        return valid; // Example validation condition
    }
}
