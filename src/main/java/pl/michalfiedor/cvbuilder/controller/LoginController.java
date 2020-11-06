package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.xml.validation.Validator;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user"})
public class LoginController {
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("user", new User());
        return "loginPage";
    }

    @PostMapping("/log")
    public String logProcess(@RequestParam String login, @RequestParam String password, Model model){
        User user = userRepository.findUserByLoginAndPassword(login, password);
        if(user!=null){
            model.addAttribute("user", user);
            if(user.getCv()!=null) {
                return "redirect:dashboard";
            }else{
                return "redirect:form1";
            }
        }
        return "redirect:login";
    }
}
