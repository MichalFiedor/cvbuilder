package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.CvGetter;
import pl.michalfiedor.cvbuilder.service.UserGetter;
import pl.michalfiedor.cvbuilder.validationGroup.AboutMeValidationGroup;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/aboutme")
public class AboutMeController {
    private final UserRepository userRepository;
    private final CvRepository cvRepository;
    private final Validator validator;

    @GetMapping("/show")
    public String showFormSecondPage(Model model){
        model.addAttribute("cv", new Cv());
        return "aboutMeForm";
    }

    @PostMapping("/add")
    public String handleSecondPageForm(@Validated({AboutMeValidationGroup.class}) Cv cv,
                                       BindingResult result, HttpSession session){
        Set<ConstraintViolation<Cv>> violations = validator.validate(cv, AboutMeValidationGroup.class);
        if(!violations.isEmpty()){
            return "aboutMeForm";
        }
        Cv userCv = CvGetter.getCvFromSession(session, cvRepository);
        userCv.setAboutMe(cv.getAboutMe());
        cvRepository.save(userCv);
        return "redirect:/experience/show";
    }

}
