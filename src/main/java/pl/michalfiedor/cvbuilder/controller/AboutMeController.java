package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.service.CvService;
import pl.michalfiedor.cvbuilder.validationGroup.AboutMeValidationGroup;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/aboutme")
public class AboutMeController {

    private final CvService cvService;
    private final Validator validator;

    @GetMapping("/show")
    public String showFormSecondPage(Model model){
        model.addAttribute("cv", new Cv());
        return "aboutMeForm";
    }

    @PostMapping("/add")
    public String handleSecondPageForm(@Validated({AboutMeValidationGroup.class}) Cv cv,
                                       BindingResult result, Principal principal, HttpSession session){
        Set<ConstraintViolation<Cv>> violations = validator.validate(cv, AboutMeValidationGroup.class);
        if(!violations.isEmpty()){
            return "aboutMeForm";
        }
        Cv userCv = cvService.getCvById(cvService.getCvIdFromSession(session));
        userCv.setAboutMe(cv.getAboutMe());
        cvService.save(userCv);

        return "redirect:/experience/show";
    }

}
