package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.City;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CityRepository;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CvFirstPageFormController {
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final CvRepository cvRepository;

    @GetMapping("/form1")
    public String showFormFirsPage(Model model){
        model.addAttribute("cv", new Cv());
        return "firstPageCvForm";
    }

    @PostMapping("/form1")
    public String handleFirstPageForm(@ModelAttribute Cv cv, HttpSession session){
        User user = UserGetter.getUserFromSession(session, userRepository);
        if(user!=null && cv!=null) {
            cvRepository.save(cv);
            user.setCv(cv);
            userRepository.save(user);
        }
        return "redirect:form2";
    }

    @ModelAttribute("cities")
    public List<City> getCitiesList(){
        return cityRepository.findAll();
    }
}
