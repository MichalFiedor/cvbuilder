package pl.michalfiedor.cvbuilder.validator;

import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.service.DateServiceImpl;
import pl.michalfiedor.cvbuilder.validator.annotation.IsAfterStartDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndDateValidator implements ConstraintValidator<IsAfterStartDate, Experience> {

    @Override
    public void initialize(IsAfterStartDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Experience experience, ConstraintValidatorContext constraintValidatorContext) {
        DateServiceImpl dateService = new DateServiceImpl();
        return dateService.isStartBeforeEndDate(experience);
    }
}
