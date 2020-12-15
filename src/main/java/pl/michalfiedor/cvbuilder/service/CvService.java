package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


//@Scope(
//        value = WebApplicationContext.SCOPE_SESSION,
//        proxyMode = ScopedProxyMode.TARGET_CLASS
//)
@Service
@RequiredArgsConstructor
public class CvService implements ICvService {
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

    public void delete(Cv cv){
        cvRepository.delete(cv);
    }

    public void deleteCvFromDataBaseAndCvFile(long id){
        Cv cv = cvRepository.findById(id).orElseThrow();
        try {
            Files.delete(Paths.get(cv.getCvPath()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        cvRepository.delete(cv);
    }
}
