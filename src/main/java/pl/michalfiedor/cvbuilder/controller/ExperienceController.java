package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.City;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.ExperienceRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/experience")
public class ExperienceController {
    private final UserRepository userRepository;
    private final CvRepository cvRepository;
    private final ExperienceRepository experienceRepository;

    @GetMapping("/show")
    public String showExperienceFormPage(Model model, HttpSession session){
        model.addAttribute("experience", new Experience());
        getExperiencesList(session, model);
        if(session.getAttribute("showNextButtonExperience")!=null){
            model.addAttribute("showNextButtonExperience", true);
        }
        return "experienceForm";
    }

    @PostMapping("/add")
    public String handleExperienceForm(@ModelAttribute Experience experience, HttpSession session){
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv userCv = user.getCv();
        if(experience.getEnd().length()==0){
            experience.setEnd("still");
        }
        experienceRepository.save(experience);
        userCv.addExperienceToCollection(experience);
        cvRepository.save(userCv);
        session.setAttribute("showNextButtonExperience", true);
        return "redirect:/experience/show";
    }

    @GetMapping("/edit/{id}")
    public String editFormExperience(@PathVariable long id, Model model){
        Experience experience = experienceRepository.findById(id).orElseThrow();
        model.addAttribute("experience", experience);
        return "experienceEditForm";
    }

    @PostMapping("/edit")
    public String editExperience(@ModelAttribute Experience experience){
        if(experienceRepository.existsById(experience.getId())){
            experienceRepository.save(experience);
        }
        return "redirect:/experience/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteExperience(@PathVariable long id){
        Experience expToDelete = experienceRepository.findById(id).orElseThrow();
        experienceRepository.delete(expToDelete);
        return "redirect:/experience/show";
    }

    public void getExperiencesList(HttpSession session, Model model){
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv userCv = user.getCv();
        model.addAttribute("experiences", userCv.getExperiences());
    }
}
