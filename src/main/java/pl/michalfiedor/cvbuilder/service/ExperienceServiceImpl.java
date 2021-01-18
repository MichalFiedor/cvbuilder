package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.repository.ExperienceRepository;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ErrorGeneratorServiceImpl errorGenerator;

    public Experience findById(long id) {
        return experienceRepository.findById(id).orElseThrow();
    }

    public void save(Experience experience) {
        experienceRepository.save(experience);
    }

    public void delete(Experience experience) {
        experienceRepository.delete(experience);
    }

    public boolean checkIfExist(long id) {
        return experienceRepository.existsById(id);
    }

    @Override
    public void showNextPageButton(HttpSession session, Model model) {
        if(session.getAttribute("showNextButtonExperience")!=null){
            model.addAttribute("showNextButtonExperience", true);
        }
    }

    @Override
    public void checkErrors(BindingResult result, Model model) {
        Map<String, String> errors = errorGenerator.generateObjectErrors(result);
        if(errors.containsKey("isAfterStartDate")){
            model.addAttribute("endDateError", errors.get("isAfterStartDate"));
        }
    }

    @Override
    public void setEndDateAsAStill(Experience experience) {
        if(experience.getEnd().length()==0){
            experience.setEnd("Still");
        }
    }
}
