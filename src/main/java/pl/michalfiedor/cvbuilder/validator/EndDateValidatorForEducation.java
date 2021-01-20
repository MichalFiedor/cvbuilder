package pl.michalfiedor.cvbuilder.validator;

import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.service.DateServiceForEducation;
import pl.michalfiedor.cvbuilder.validator.annotation.IsAfterStartDateForEducation;
import pl.michalfiedor.cvbuilder.validator.annotation.IsAfterStartDateForExperience;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndDateValidatorForEducation implements ConstraintValidator<IsAfterStartDateForEducation, EducationDetails> {

    @Override
    public void initialize(IsAfterStartDateForEducation constraintAnnotation) {
    }

    @Override
    public boolean isValid(EducationDetails educationDetails, ConstraintValidatorContext constraintValidatorContext) {
        DateServiceForEducation dateServiceForEducation = new DateServiceForEducation();
        return dateServiceForEducation.isStartBeforeEndDate(educationDetails);
    }
}
