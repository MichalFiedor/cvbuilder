package pl.michalfiedor.cvbuilder.validator.annotation;

import pl.michalfiedor.cvbuilder.validator.EndDateValidatorForEducation;
import pl.michalfiedor.cvbuilder.validator.EndDateValidatorForExperience;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndDateValidatorForEducation.class)

public @interface IsAfterStartDateForEducation {
    String message() default "End date must be past start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
