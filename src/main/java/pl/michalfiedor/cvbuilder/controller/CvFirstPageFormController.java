package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.City;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CityRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CvFirstPageFormController {
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    @GetMapping("/form1")
    public String showFormFirsPage(Model model){
        model.addAttribute("user", new User());
        return "firstPageCvForm";
    }

    @PostMapping("/form1")
    @ResponseBody
    public String updateUserEntityWithDataFromFirstPageForm(@ModelAttribute User user, HttpSession session){
        User userFromSession = (User) session.getAttribute("user");

        if(userFromSession!=null) {
            User userToUpdate = userRepository.findById(userFromSession.getId()).orElseThrow(
                    () -> new RuntimeException("User doesn't exist"));
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
            userToUpdate.setCity(user.getCity());
            userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("Session without actual logged user");
        }
        return "redirect:form2";
    }

    @ModelAttribute("cities")
    public List<City> getCitiesList(){
        return cityRepository.findAll();
    }


    @GetMapping("/form2")
    public String showFormSecondPage(){
        return "secondPageCvForm";
    }

}
