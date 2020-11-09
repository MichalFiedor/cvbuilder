package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.servlet.http.HttpSession;

public class UserGetter {
    public static User getUserFromSession(HttpSession session, UserRepository userRepository) {
        long id = (Long) session.getAttribute("id");
        return userRepository.findById(id).get();
    }
}
