package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.servlet.http.HttpSession;

public class CvGetter {
    public static Cv getCvFromSession(HttpSession session, CvRepository cvRepository) {
        long id = (Long) session.getAttribute("cvId");
        return cvRepository.findById(id).get();
    }
}
