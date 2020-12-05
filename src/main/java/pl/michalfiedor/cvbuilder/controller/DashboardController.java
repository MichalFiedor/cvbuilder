package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final CvRepository cvRepository;

    @GetMapping("/show")
    public String showDashboard(Model model){
        List<Cv> cvs = cvRepository.findAll();
        for(Cv cv : cvs){
            if (cv.getCvPath()==null){
                cvRepository.delete(cv);
            }
        }
        cvs=cvRepository.findAll();
        if(cvs.size()>0){
            model.addAttribute("cvs", cvs);
        }
        return "dashboard";
    }

}
