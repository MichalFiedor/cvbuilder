package pl.michalfiedor.cvbuilder.validator;

import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.service.DateService;
import pl.michalfiedor.cvbuilder.validator.annotation.IsAfterStartDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndDateValidator implements ConstraintValidator<IsAfterStartDate, Experience> {

    @Override
    public void initialize(IsAfterStartDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(Experience experience, ConstraintValidatorContext constraintValidatorContext) {
        DateService dateService = new DateService();
        return dateService.isStartBeforeEndDate(experience);
    }
}
