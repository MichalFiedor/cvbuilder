package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.michalfiedor.cvbuilder.exception.UserAlreadyExistException;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.service.MyUserDetailsService;
import pl.michalfiedor.cvbuilder.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final MyUserDetailsService userDetailsService;

    @GetMapping("/registration")
    public String showIndexPage(Model model){
        model.addAttribute("user", new User());
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "registrationPage";
        }
        try{
            userService.registerNewUserAccount(user);
        } catch (UserAlreadyExistException userAlreadyExistException){
            model.addAttribute("emailFailed", true);
            return "registrationPage";
        }
        userDetailsService.loadUserByUsername(user.getEmail());
        return "redirect:login";
    }
}
