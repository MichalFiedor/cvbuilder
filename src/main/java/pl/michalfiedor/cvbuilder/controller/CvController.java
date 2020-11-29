package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.repository.CvRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cv")
public class CvController {
    private final CvRepository cvRepository;

    @GetMapping("/delete/{id}")
    public String deleteCv(@PathVariable long id){
        Cv cv = cvRepository.findById(id).orElseThrow();
        try {
            Files.delete(Paths.get(cv.getCvPath()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        cvRepository.deleteById(id);
        return "redirect:/dashboard/show";
    }
}
