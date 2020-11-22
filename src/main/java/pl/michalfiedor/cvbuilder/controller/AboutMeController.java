package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/aboutme")
public class AboutMeController {
    private final UserRepository userRepository;
    private final CvRepository cvRepository;

    @GetMapping("/show")
    public String showFormSecondPage(Model model){
        model.addAttribute("cv", new Cv());
        return "aboutMeForm";
    }

    @PostMapping("/add")
    public String handleSecondPageForm(@Valid Cv cv, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "aboutMeForm";
        }
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv userCv = user.getCv();
        userCv.setAboutMe(cv.getAboutMe());
        cvRepository.save(userCv);
        return "redirect:/experience/show";
    }

}
