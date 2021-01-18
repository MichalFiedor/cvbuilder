package pl.michalfiedor.cvbuilder.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.michalfiedor.cvbuilder.model.Experience;

import javax.servlet.http.HttpSession;

public interface ExperienceService {

    Experience findById(long id);

    void save(Experience experience);

    void delete(Experience experience);

    boolean checkIfExist(long id);

    void showNextPageButton(HttpSession session, Model model);

    void checkErrors(BindingResult result, Model model);

    void setEndDateAsAStill(Experience experience);

}
