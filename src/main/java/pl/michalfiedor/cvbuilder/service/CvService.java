package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CvService {
    private final CvRepository cvRepository;

    public Cv getCvById(long id) {
        return cvRepository.findById(id).orElseThrow();
    }

    public long getCvIdFromSession(HttpSession session){
        return (long)session.getAttribute("cvId");
    }

    public void save(Cv cv){
        cvRepository.save(cv);
    }

    public List<Cv> findAll() {
        return cvRepository.findAll();
    }
}
