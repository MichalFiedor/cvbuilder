package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.Cv;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CvService {
    Cv getCvById(long id);

    long getCvIdFromSession(HttpSession session);

    void save(Cv cv);

    List<Cv> findAll();

    void delete(Cv cv);

    void deleteCvFromDataBaseAndCvFile(long id);

}
