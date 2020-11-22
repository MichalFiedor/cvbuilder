package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserRepository userRepository;

    @GetMapping("/registration")
    public String showIndexPage(Model model){
        model.addAttribute("user", new User());
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String userRegistration(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "registrationPage";
        }
        userRepository.save(user);
        return "redirect:login";
    }
}
