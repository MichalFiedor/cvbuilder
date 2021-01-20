package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.City;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.service.*;
import pl.michalfiedor.cvbuilder.service.UserService;
import pl.michalfiedor.cvbuilder.validationGroup.BasicDataValidationGroup;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basicdata")
public class BasicDataController {
    private final CityService cityService;
    private final UserService userService;
    private final BasicDataService basicDataService;
    private final Validator validator;

    @GetMapping("/show")
    public String showFormForBasicData(Model model){
        model.addAttribute("cv", new Cv());
        return "basicDataForm";
    }

    @PostMapping("/add")
    public String handleBasicDataForm(@Validated({BasicDataValidationGroup.class}) Cv cv,
                                      BindingResult result, Principal principal, HttpSession session){
        Set<ConstraintViolation<Cv>> violations = validator.validate(cv, BasicDataValidationGroup.class);
        if(!violations.isEmpty()){
            return "basicDataForm";
        }
        basicDataService.saveBasicData(cv, session, principal);
        return "redirect:/aboutme/show";
    }

    @ModelAttribute("cities")
    public List<City> getCitiesList(){
        return cityService.getCities();
    }

    @ModelAttribute("userEmail")
    public String getUserEmail(Principal principal){
        return principal.getName();
    }
}
