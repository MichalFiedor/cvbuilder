package pl.michalfiedor.cvbuilder.validator.annotation;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import pl.michalfiedor.cvbuilder.validator.EndDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndDateValidator.class)
public @interface IsAfterStartDate {
    String message() default "End date must be past start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
