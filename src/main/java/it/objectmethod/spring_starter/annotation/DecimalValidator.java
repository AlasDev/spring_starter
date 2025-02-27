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
    public boolean isValid(Double num, ConstraintValidatorContext context) {

        if (num == null) {
            return true;
        }

        int decimal;
        List<String> list = List.of(num.toString().split("\\."));
        if (list.size() == 1) {
            //list size 1 means that .split() didn't split anything, it doesn't have any decimals
            decimal = 0;
        } else {
            decimal = list.get(1).length();
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Value must have minimum '" + minDecimalDigits + "' and maximum '" + maxDecimalDigits + "' decimal digits.").addConstraintViolation();

        return decimal >= minDecimalDigits && decimal <= maxDecimalDigits;
    }
}
