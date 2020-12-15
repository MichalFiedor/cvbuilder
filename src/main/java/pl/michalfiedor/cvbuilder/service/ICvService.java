package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.Cv;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ICvService {
    Cv getCvById(long id);

    long getCvIdFromSession(HttpSession session);

    void save(Cv cv);

    List<Cv> findAll();

    void delete(Cv cv);

    public void deleteCvFromDataBaseAndCvFile(long id);
}
