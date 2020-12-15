package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.michalfiedor.cvbuilder.service.ICvService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cv")
public class CvController {
    private final ICvService cvService;

    @GetMapping("/delete/{id}")
    public String deleteCv(@PathVariable long id){
        cvService.deleteCvFromDataBaseAndCvFile(id);
        return "redirect:/dashboard/show";
    }
}
