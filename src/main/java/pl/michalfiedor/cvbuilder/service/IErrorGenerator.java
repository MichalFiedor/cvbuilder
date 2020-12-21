package pl.michalfiedor.cvbuilder.service;

import org.springframework.validation.BindingResult;

import java.util.Map;

public interface IErrorGenerator {
    Map<String, String> generateObjectErrors(BindingResult result);
}
