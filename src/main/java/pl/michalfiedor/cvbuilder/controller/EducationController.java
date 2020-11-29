package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.*;
import pl.michalfiedor.cvbuilder.service.CityService;
import pl.michalfiedor.cvbuilder.service.CvService;
import pl.michalfiedor.cvbuilder.service.EducationDetailsService;
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
    private final UniversityService universityService;
    private final CityService cityService;
    private final EducationDetailsService educationDetailsService;
    private final Validator validator;
    private final CvService cvService;

    @GetMapping("/show")
    public String showEducationFormPage(Model model, HttpSession session){

        getEducationList(session, model);
        if(session.getAttribute("showNextButton")!=null){
            model.addAttribute("showNextButton", true);
        }
        return "educationForm";
    }

    @PostMapping("/university")
    public String showUniversityList(@RequestParam long cityId, Model model, HttpSession session){

        List<University> universitiesList = universityService.findAllByCityId(cityId);
        model.addAttribute("universitiesPerCity", universitiesList);
        model.addAttribute("educationDetails", new EducationDetails());
        model.addAttribute("selectedCity", cityService.findById(cityId));
        getEducationList(session, model);

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
            getEducationList(session, model);
            return "educationForm";
        }
        Cv userCv = cvService.getCvById(cvService.getCvIdFromSession(session));
        if(educationDetails.getEnd().length()==0){
            educationDetails.setEnd("Still");
        }
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

//    @ModelAttribute("universities")
//    public List<University> getUniversitiesList(){
//
//        return universityRepository.findAll();
//    }

    @ModelAttribute("cities")
    public List<City> getCitiesList(){

        return cityService.getCities();
    }

    public void getEducationList(HttpSession session, Model model){
        Cv userCv = cvService.getCvById(cvService.getCvIdFromSession(session));
        model.addAttribute("educationList", userCv.getEducationDetailsList());
    }

}
