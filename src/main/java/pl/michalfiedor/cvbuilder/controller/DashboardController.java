package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.service.CvService;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final CvService cvService;

    @GetMapping("/show")
    public String showDashboard(Model model){
        List<Cv> cvs = cvService.findAll();
        for(Cv cv : cvs){
            if (cv.getCvPath()==null){
                cvService.delete(cv);
            }
        }
        cvs=cvService.findAll();
        if(cvs.size()>0){
            model.addAttribute("cvs", cvs);
        }
        return "dashboard";
    }
}
