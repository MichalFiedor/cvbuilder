package pl.michalfiedor.cvbuilder.service;

import org.springframework.ui.Model;
import pl.michalfiedor.cvbuilder.model.EducationDetails;

import javax.servlet.http.HttpSession;

public interface EducationDetailsService {
    EducationDetails findById(long id);

    void save(EducationDetails educationDetails);

    void delete(long id);

    void getEducationList(HttpSession session, Model model);
}
