package pl.michalfiedor.cvbuilder.service;

import org.springframework.ui.Model;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.model.Experience;

import javax.servlet.http.HttpSession;

public interface EducationDetailsService {
    EducationDetails findById(long id);

    void save(EducationDetails educationDetails);

    void delete(long id);

    void getEducationList(HttpSession session, Model model);

    void setEndDateAsAStill(EducationDetails educationDetails);
}
