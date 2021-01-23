package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Cv;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AboutMeService {
    private final CvService cvService;

    public void save(Cv cv, HttpSession session){
        Cv userCv = cvService.getById(cvService.getCvIdFromSession(session));
        userCv.setAboutMe(cv.getAboutMe());
        cvService.save(userCv);
    }
}
