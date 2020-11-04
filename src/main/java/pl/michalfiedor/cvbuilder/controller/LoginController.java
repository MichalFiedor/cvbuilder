package pl.michalfiedor.cvbuilder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showIndexPage(){
        return "loginPage";
    }
}
