package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;

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
    public String handleSecondPageForm(@RequestParam String aboutMe, HttpSession session){
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv userCv = user.getCv();
        userCv.setAboutMe(aboutMe);
        cvRepository.save(userCv);
        return "redirect:/experience/show";
    }

}
