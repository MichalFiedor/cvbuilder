package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ErrorsCheckerForEndDateValidation {
    private final ErrorGeneratorService errorGenerator;

    public void checkErrors(BindingResult result, Model model, String validationName) {
        Map<String, String> errors = errorGenerator.generateObjectErrors(result);
        if(errors.containsKey(validationName)){
            model.addAttribute("endDateError", errors.get(validationName));
        }
    }

}
