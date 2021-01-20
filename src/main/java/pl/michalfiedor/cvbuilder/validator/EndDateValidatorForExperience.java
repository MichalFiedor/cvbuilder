package pl.michalfiedor.cvbuilder.validator;

import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.service.DateServiceForExperience;
import pl.michalfiedor.cvbuilder.validator.annotation.IsAfterStartDateForExperience;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndDateValidatorForExperience implements ConstraintValidator<IsAfterStartDateForExperience, Experience> {

    @Override
    public void initialize(IsAfterStartDateForExperience constraintAnnotation) {
    }

    @Override
    public boolean isValid(Experience experience, ConstraintValidatorContext constraintValidatorContext) {
        DateServiceForExperience dateService = new DateServiceForExperience();
        return dateService.isStartBeforeEndDate(experience);
    }
}
