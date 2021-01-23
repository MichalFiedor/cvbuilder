package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/experience")
public class ExperienceController {
    private final ExperienceService experienceService;
    private final ErrorsCheckerForEndDateValidation errorsCheckerForEndDateValidation;
    private final NextPageButtonService nextPageButtonService;

    @GetMapping("/show")
    public String showExperienceFormPage(Model model, HttpSession session){
        model.addAttribute("experience", new Experience());
        experienceService.passExperiencesListToView(session, model);
        nextPageButtonService.showNextPageButtonOnPage(session, model,"showNextButtonExperience");
        return "experienceForm";
    }

    @PostMapping("/add")
    public String handleExperienceForm(@Valid Experience experience, BindingResult result, Model model,
                                       HttpSession session){
        errorsCheckerForEndDateValidation.checkErrors(result, model, "IsAfterStartDateForExperience");
        if(result.hasErrors()){
            experienceService.passExperiencesListToView(session, model);
            return "experienceForm";
        }
        experienceService.addExperienceToCv(session, experience);
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
        experienceService.saveEdited(experience);
        return "redirect:/experience/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteExperience(@PathVariable long id){
        Experience expToDelete = experienceService.findById(id);
        experienceService.delete(expToDelete);
        return "redirect:/experience/show";
    }
}
