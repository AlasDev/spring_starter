package it.objectmethod.spring_starter.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Min;

import java.util.List;

public class DecimalValidator implements ConstraintValidator<DecimalValidation, Double> {

    @Min(value = 0, message = "'@DecimalValidator' cannot start to validate because 'max' is not valid.")
    int maxDecimalDigits;

    @Min(value = 0, message = "'@DecimalValidator' cannot start to validate because 'min' is not valid.")
    int minDecimalDigits;

    @Override
    public void initialize(DecimalValidation decimalValidation) {
        this.maxDecimalDigits = decimalValidation.max();
        this.minDecimalDigits = decimalValidation.min();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        List<String> list = List.of(value.toString().split("\\."));
        int decimal = list.get(1).length();

        return decimal <= maxDecimalDigits && decimal >= minDecimalDigits;
    }
}
