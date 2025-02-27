package it.objectmethod.spring_starter.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = TimeComparatorValidator.class)
public @interface TimeComparatorValidation {

    String message() default "Invalid time difference between dates in object.";

    int maxHours() default 24;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}