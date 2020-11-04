package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String showIndexPage(Model model){
        model.addAttribute("user", new User());
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:login";
    }
}
