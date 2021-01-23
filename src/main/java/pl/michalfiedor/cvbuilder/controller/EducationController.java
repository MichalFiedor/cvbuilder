package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.*;
import pl.michalfiedor.cvbuilder.service.*;
import pl.michalfiedor.cvbuilder.service.UniversityService;
import pl.michalfiedor.cvbuilder.validationGroup.EducationDetailValidationGroup;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/education")
public class EducationController {
    private final CityService cityService;
    private final EducationDetailsService educationDetailsService;
    private final Validator validator;
    private final ErrorsCheckerForEndDateValidation errorsCheckerForEndDateValidation;
    private final NextPageButtonService nextPageButtonService;

    @GetMapping("/show")
    public String showEducationFormPage(Model model, HttpSession session){
        educationDetailsService.getEducationsList(session, model);
        nextPageButtonService.showNextPageButtonOnPage(session, model, "showNextButton");
        return "educationForm";
    }

    @PostMapping("/university")
    public String showUniversityList(@RequestParam long cityId, Model model, HttpSession session){
        educationDetailsService.passUniversitiesListForChosenCity(session, cityId, model, true);
        return "educationForm";
    }

    @PostMapping("/add")
    public String handleEducationForm(@RequestParam long cityId, @Validated({EducationDetailValidationGroup.class}) EducationDetails educationDetails,
                               BindingResult result, HttpSession session, Model model){

        Set<ConstraintViolation<EducationDetails>> violations = validator.validate(
                educationDetails, EducationDetailValidationGroup.class);
        errorsCheckerForEndDateValidation.checkErrors(result, model,"IsAfterStartDateForEducation");
        if(!violations.isEmpty()){
            educationDetailsService.passUniversitiesListForChosenCity(session, cityId, model, false);
            return "educationForm";
        }
        educationDetailsService.addEducationToCv(session, educationDetails);
        session.setAttribute("showNextButton", true);
        return "redirect:/education/show";
    }

    @GetMapping("/edit/{id}")
    public String editFormEducation(@PathVariable long id, Model model){
        EducationDetails educationDetails = educationDetailsService.findById(id);
        model.addAttribute("educationDetails", educationDetails);
        return "experienceEditForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteEducation(@PathVariable long id){
        educationDetailsService.delete(id);
        return "redirect:/education/show";
    }

    @ModelAttribute("cities")
    public List<City> getCitiesList(){
        return cityService.get();
    }
}
