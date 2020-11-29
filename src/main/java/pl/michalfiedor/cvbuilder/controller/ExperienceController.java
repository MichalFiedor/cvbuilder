package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.service.CvService;
import pl.michalfiedor.cvbuilder.service.ExperienceService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/experience")
public class ExperienceController {
    private final CvService cvService;
    private final ExperienceService experienceService;

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
    public String handleExperienceForm(@Valid Experience experience, BindingResult result, Model model,
                                       HttpSession session){
        Cv userCv = cvService.getCvById(cvService.getCvIdFromSession(session));
        if(result.hasErrors()){
            if(userCv.getExperiences().size()>0){
                getExperiencesList(session, model);
            }
            return "experienceForm";
        }
        if(experience.getEnd().length()==0){
            experience.setEnd("still");
        }
        experienceService.save(experience);
        userCv.addExperienceToCollection(experience);
        cvService.save(userCv);
        session.setAttribute("showNextButtonExperience", true);
        return "redirect:/experience/show";
    }

    @GetMapping("/edit/{id}")
    public String editFormExperience(@PathVariable long id, Model model){
        Experience experience = experienceService.findById(id);
        model.addAttribute("experience", experience);
        return "experienceEditForm";
    }

    @PostMapping("/edit")
    public String editExperience(@Valid Experience experience, BindingResult result){
        if (result.hasErrors()){
            return "experienceEditForm";
        }
        if(experienceService.checkIfExist(experience.getId())){
            if(experience.getEnd().length()==0){
                experience.setEnd("still");
            }
            experienceService.save(experience);
        }
        return "redirect:/experience/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteExperience(@PathVariable long id){
        Experience expToDelete = experienceService.findById(id);
        experienceService.delete(expToDelete);
        return "redirect:/experience/show";
    }

    public void getExperiencesList(HttpSession session, Model model){
        Cv userCv = cvService.getCvById(cvService.getCvIdFromSession(session));
        model.addAttribute("experiences", userCv.getExperiences());
    }
}
