package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CityRepository;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CvSecondPageFormController {
    private final UserRepository userRepository;
    private final CvRepository cvRepository;

    @GetMapping("/form2")
    public String showFormSecondPage(Model model){
        model.addAttribute("cv", new Cv());
        return "secondPageCvForm";
    }

    @PostMapping("/form2")
    public String handleSecondPageForm(@ModelAttribute Cv cv, HttpSession session){
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv userCv = user.getCv();
        userCv.setAboutMe(cv.getAboutMe());
        cvRepository.save(userCv);
        return "thirdPageCvForm";
    }

}
