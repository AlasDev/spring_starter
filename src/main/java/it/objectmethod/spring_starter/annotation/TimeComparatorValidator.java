package it.objectmethod.spring_starter.annotation;

import it.objectmethod.spring_starter.dto.CorsaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public class TimeComparatorValidator implements ConstraintValidator<TimeComparatorValidation, CorsaDTO> {

    @Min(value = 0)
    int timeMax;

    @Override
    public void initialize(TimeComparatorValidation timeComparatorValidation){
        this.timeMax = timeComparatorValidation.maxHours();
    }

    @Override
    public boolean isValid(CorsaDTO c, ConstraintValidatorContext context) {
        LocalDateTime start = c.getDataInizio();
        LocalDateTime end = c.getDataFine();

        if (start == null || end == null) {
            return true;
        }

        LocalDateTime maxAllowedEnd = start.plusHours(timeMax);

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("'" + start + "' and '" + end + "' should not be more than '" + timeMax + "h' apart from each other.").addConstraintViolation();

        return end.isBefore(maxAllowedEnd);
    }
}
