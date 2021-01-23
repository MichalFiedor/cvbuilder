package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.repository.ExperienceRepository;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final CvService cvService;

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

    public void setEndDateAsAStill(Experience experience) {
        if (experience.getEnd().length() == 0) {
            experience.setEnd("Still");
        }
    }

    public void passExperiencesListToView(HttpSession session, Model model) {
        Cv userCv = cvService.getById(cvService.getCvIdFromSession(session));
        model.addAttribute("experiences", userCv.getExperiences());
    }

    public void passExperiencesListToViewIfListIsNotEmpty(HttpSession session, Model model, Cv userCv) {
        if (userCv.getExperiences().size() > 0) {
            passExperiencesListToView(session, model);
        }
    }

    public void addExperienceToCv(HttpSession session, Experience experience) {
        Cv userCv = cvService.getById(cvService.getCvIdFromSession(session));
        setEndDateAsAStill(experience);
        save(experience);
        userCv.addExperienceToCollection(experience);
        cvService.save(userCv);
    }
    public void saveEdited(Experience experience){
        if(checkIfExist(experience.getId())){
            setEndDateAsAStill(experience);
            save(experience);
        }
    }

}
