package models.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = EanValidator.class)
@Target( { FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EAN {
    String message() default "error.invalid.ean";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}