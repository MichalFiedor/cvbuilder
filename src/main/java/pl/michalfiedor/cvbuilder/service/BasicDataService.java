package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Service
@RequiredArgsConstructor
public class BasicDataService {
    private final CvService cvService;
    private final UserService userService;

    public void save(Cv cv, HttpSession session, Principal principal){
        User user = userService.get(principal.getName());
        if(user!=null && cv!=null) {
            cvService.save(cv);
            session.setAttribute("cvId", cv.getId());
            user.addCv(cv);
            userService.save(user);
        }
    }
}
