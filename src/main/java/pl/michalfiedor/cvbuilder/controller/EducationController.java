package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.*;
import pl.michalfiedor.cvbuilder.service.*;
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
    private final UniversityService universityService;
    private final CityService cityService;
    private final EducationDetailsService educationDetailsService;
    private final Validator validator;
    private final CvService cvService;

    @GetMapping("/show")
    public String showEducationFormPage(Model model, HttpSession session){

        educationDetailsService.getEducationList(session, model);
        if(session.getAttribute("showNextButton")!=null){
            model.addAttribute("showNextButton", true);
        }
        return "educationForm";
    }

    @PostMapping("/university")
    public String showUniversityList(@RequestParam long cityId, Model model, HttpSession session){

        session.removeAttribute("showNextButton");
        List<University> universitiesList = universityService.findAllByCityId(cityId);
        model.addAttribute("universitiesPerCity", universitiesList);
        model.addAttribute("educationDetails", new EducationDetails());
        model.addAttribute("selectedCity", cityService.findById(cityId));
        educationDetailsService.getEducationList(session, model);

        return "educationForm";
    }

    @PostMapping("/add")
    public String addEducation(@RequestParam long cityId, @Validated({EducationDetailValidationGroup.class}) EducationDetails educationDetails,
                               BindingResult result, HttpSession session, Model model){

        Set<ConstraintViolation<EducationDetails>> violations = validator.validate(
                educationDetails, EducationDetailValidationGroup.class);

        if(!violations.isEmpty()){
            List<University> universitiesList = universityService.findAllByCityId(cityId);
            model.addAttribute("selectedCity", cityService.findById(cityId));
            model.addAttribute("universitiesPerCity", universitiesList);
            educationDetailsService.getEducationList(session, model);
            return "educationForm";
        }

        Cv userCv = cvService.getCvById(cvService.getCvIdFromSession(session));
        educationDetailsService.setEndDateAsAStill(educationDetails);
        educationDetailsService.save(educationDetails);
        userCv.addEducationDetailToCollection(educationDetails);
        cvService.save(userCv);
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
        return cityService.getCities();
    }
}
