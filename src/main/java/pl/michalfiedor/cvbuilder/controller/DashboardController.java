package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user"})
public class DashboardController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public String showDashboardPage(){
        return "dashboard";
    }
}
