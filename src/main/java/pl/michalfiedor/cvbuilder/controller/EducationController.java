package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.xmpbox.type.BooleanType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.*;
import pl.michalfiedor.cvbuilder.repository.*;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/education")
public class EducationController {
    private final UserRepository userRepository;
    private final CvRepository cvRepository;
    private final UniversityRepository universityRepository;
    private final CityRepository cityRepository;
    private final EducationDetailsRepository educationDetailsRepository;

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
        List<University> universitiesList = universityRepository.findAllByCityId(cityId);
        model.addAttribute("universitiesPerCity", universitiesList);
        model.addAttribute("educationDetails", new EducationDetails());
        model.addAttribute("selectedCity", cityRepository.findById(cityId).orElseThrow());
        getEducationList(session, model);
        session.setAttribute("showNextButton", true);

        return "educationForm";
    }

    @PostMapping("/add")
    public String addEducation(@ModelAttribute EducationDetails educationDetails,
                               HttpSession session, Model model){
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv userCv = user.getCv();
        if(educationDetails.getEnd().length()==0){
            educationDetails.setEnd("Still");
        }
        educationDetailsRepository.save(educationDetails);
        userCv.addEducationDetailToCollection(educationDetails);
        cvRepository.save(userCv);

        return "redirect:/education/show";
    }

    @GetMapping("/edit/{id}")
    public String editFormEducation(@PathVariable long id, Model model){
        EducationDetails educationDetails = educationDetailsRepository.findById(id).orElseThrow();
        model.addAttribute("educationDetails", educationDetails);
        return "experienceEditForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteEducation(@PathVariable long id){
        EducationDetails educationDetails = educationDetailsRepository.findById(id).orElseThrow();
        educationDetailsRepository.delete(educationDetails);
        return "redirect:/education/show";
    }

    @ModelAttribute("universities")
    public List<University> getUniversitiesList(){
        return universityRepository.findAll();
    }

    @ModelAttribute("cities")
    public List<City> getCitiesList(){
        return cityRepository.findAll();
    }

    public void getEducationList(HttpSession session, Model model){
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv userCv = user.getCv();
        model.addAttribute("educationList", userCv.getEducationDetailsList());
    }

}
