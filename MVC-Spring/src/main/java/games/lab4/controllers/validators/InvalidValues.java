package games.lab4.controllers.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvalidValuesValidator.class)
public @interface InvalidValues {
    String message() default "Illegal login";
            Class<?>[] groups() default {};
            Class<? extends Payload>[] payload() default {};
            String[] niedozwoloneWartosci() default {};
            boolean ignoruj() default false;
}
