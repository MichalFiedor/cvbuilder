package pl.michalfiedor.cvbuilder.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class ErrorGeneratorService {

    public Map<String, String> generateObjectErrors(BindingResult result){
        Map<String, String> fieldErrorMap = new HashMap<>();
        result.getGlobalErrors().forEach(objectError -> {
            if(objectError.getCode().contains("IsAfterStartDateForExperience")){
                fieldErrorMap.put("IsAfterStartDateForExperience", objectError.getDefaultMessage());
            }
        });
        return fieldErrorMap;
    }
}
